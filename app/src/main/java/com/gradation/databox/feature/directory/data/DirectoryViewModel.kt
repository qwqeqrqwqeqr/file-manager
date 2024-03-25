package com.gradation.databox.feature.directory.data

import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.gradation.databox.core.ui.navigation.Key.DIRECTORY_KEY
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.model.PathTree
import com.gradation.databox.data.file.utils.getPathTreeList
import com.gradation.databox.data.file.utils.toDataboxFileTypeList
import com.gradation.databox.feature.directory.data.model.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DirectoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private var _directoryPath: MutableStateFlow<String> =
        MutableStateFlow(savedStateHandle[DIRECTORY_KEY] ?: "")
    val directoryPath: StateFlow<String> = _directoryPath.asStateFlow()

    val pathTreeList: StateFlow<List<PathTree>> = directoryPath.map { it.getPathTreeList() } .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )



    fun getSortTypeEntries(): List<SortType> = listOf(
        SortType.Name(), SortType.Size(), SortType.LastModifiedTime(), SortType.CreateTime()
    )

}