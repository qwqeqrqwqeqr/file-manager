package com.gradation.databox.feature.directory.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.databox.core.common.state.DataState
import com.gradation.databox.core.ui.navigation.Key.DIRECTORY_KEY
import com.gradation.databox.data.datastore.datasource.DataStoreDataSource
import com.gradation.databox.core.common.event.EventManager
import com.gradation.databox.data.file.datasource.FileDataSource
import com.gradation.databox.di.DispatcherProvider
import com.gradation.databox.domain.model.file.FileType
import com.gradation.databox.domain.model.file.PathTree
import com.gradation.databox.domain.model.type.AscendingType
import com.gradation.databox.domain.model.type.SortType
import com.gradation.databox.feature.directory.data.state.DirectoryUiState
import com.gradation.databox.feature.directory.data.state.TypeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DirectoryViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    dataStoreDataSource: DataStoreDataSource,
    fileDataSource: FileDataSource,
    savedStateHandle: SavedStateHandle,
    eventManager: EventManager
) : ViewModel() {


    private var _directoryPath: MutableStateFlow<String> =
        MutableStateFlow(savedStateHandle[DIRECTORY_KEY] ?: "")
    val directoryPath: StateFlow<String> = _directoryPath.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val pathTreeList: StateFlow<List<PathTree>> =
        directoryPath.flatMapLatest {
            fileDataSource.getPathTreeList(it).map { result ->
                when (result) {
                    is DataState.Fail -> emptyList()
                    is DataState.Success -> result.data
                }
            }
        }
            .flowOn(dispatcherProvider.default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    val typeState = TypeState(dataStoreDataSource, viewModelScope)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val fileList: Flow<DataState<List<FileType>>> =
        combine(directoryPath, eventManager.eventState) { path, _ ->
            fileDataSource.getFileTypeList(path)
        }.flatMapLatest { it }


    val directoryUiState: StateFlow<DirectoryUiState> =
        combine(
            fileList,
            dataStoreDataSource.ascendingType,
            dataStoreDataSource.sortType
        ) { fileList, ascendingType, sortType ->
            when (fileList) {
                is DataState.Fail -> DirectoryUiState.Empty
                is DataState.Success ->
                    when (ascendingType) {
                        AscendingType.Ascending ->
                            when (sortType) {
                                is SortType.CreateTime -> fileList.data.sortedBy { it.creationTime }
                                is SortType.LastModifiedTime -> fileList.data.sortedBy { it.lastModifiedTime }
                                is SortType.Name -> fileList.data.sortedBy { it.name }
                                is SortType.Size -> fileList.data.sortedBy { it.size }
                            }

                        AscendingType.Descending ->
                            when (sortType) {
                                is SortType.CreateTime -> fileList.data.sortedByDescending { it.creationTime }
                                is SortType.LastModifiedTime -> fileList.data.sortedByDescending { it.lastModifiedTime }
                                is SortType.Name -> fileList.data.sortedByDescending { it.name }
                                is SortType.Size -> fileList.data.sortedByDescending { it.size }
                            }
                    }.let { list ->
                        if (list.isEmpty()) DirectoryUiState.Empty
                        else DirectoryUiState.Success(list)
                    }
            }
        }.flowOn(dispatcherProvider.default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DirectoryUiState.Loading
            )

}