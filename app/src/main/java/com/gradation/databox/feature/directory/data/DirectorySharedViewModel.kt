package com.gradation.databox.feature.directory.data

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.databox.core.common.state.DataState
import com.gradation.databox.core.common.state.EventState
import com.gradation.databox.core.ui.navigation.Key
import com.gradation.databox.data.datastore.datasource.DataStoreDataSource
import com.gradation.databox.core.common.event.EventManager
import com.gradation.databox.data.file.datasource.FileDataSource
import com.gradation.databox.di.DispatcherProvider
import com.gradation.databox.domain.model.file.FileType
import com.gradation.databox.domain.model.file.PathTree
import com.gradation.databox.domain.model.type.AscendingType
import com.gradation.databox.domain.model.type.SortType
import com.gradation.databox.feature.directory.data.state.DirectoryUiState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.ModeState
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
class DirectorySharedViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    dataStoreDataSource: DataStoreDataSource,
    fileDataSource: FileDataSource,
    eventManager: EventManager
) : ViewModel() {

    var directoryPath: MutableStateFlow<String> = MutableStateFlow("")
    val updateDirectoryPath: (String) -> Unit = { directoryPath.value = it }


    val fileState: FileState =
        FileState(eventManager.updateEventState, fileDataSource, viewModelScope)
    val modeState: ModeState = ModeState()


    val updateEventState: (EventState) -> Unit = { eventManager.updateEventState(it) }
    val eventState: MutableStateFlow<EventState> = eventManager.eventState

}