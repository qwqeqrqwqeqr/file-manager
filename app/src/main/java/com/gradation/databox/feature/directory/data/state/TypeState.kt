package com.gradation.databox.feature.directory.data.state

import com.gradation.databox.data.datastore.datasource.DataStoreDataSource
import com.gradation.databox.domain.model.type.AscendingType
import com.gradation.databox.domain.model.type.SortType
import com.gradation.databox.domain.model.type.ViewType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TypeState(
    dataStoreDataSource: DataStoreDataSource,
    viewModelScope: CoroutineScope
) {
    var sortType: StateFlow<SortType> = dataStoreDataSource.sortType.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SortType.Name
    )
    var viewType: StateFlow<ViewType> = dataStoreDataSource.viewType.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ViewType.List
    )
    var ascendingType: StateFlow<AscendingType> = dataStoreDataSource.ascendingType.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AscendingType.Ascending
    )


    val updateSortType: (SortType) -> Unit = { type ->
        viewModelScope.launch {
            dataStoreDataSource.updateSortType(type)
        }
    }
    val updateAscendingType: (AscendingType) -> Unit = { type ->
        viewModelScope.launch {
            dataStoreDataSource.updateAscendingType(type)
        }
    }

    val updateViewType: (ViewType) -> Unit = { type ->
        viewModelScope.launch {
            dataStoreDataSource.updateViewType(type)
        }
    }


    fun getTitleName(sortType: SortType):String =
        when(sortType){
            SortType.CreateTime -> "생성날짜"
            SortType.LastModifiedTime -> "수정날짜"
            SortType.Name -> "이름"
            SortType.Size -> "크기"
        }


    fun getTitleName(ascendingType: AscendingType):String =
        when(ascendingType){
            AscendingType.Ascending -> "오름차순"
            AscendingType.Descending -> "내림차순"
        }

    fun getTitleName(viewType: ViewType):String =
        when(viewType){
            ViewType.List -> "리스트"
            ViewType.Grid -> "그리드"
        }


    val sortTypeEntries: List<SortType> = listOf(
        SortType.Name, SortType.Size, SortType.LastModifiedTime, SortType.CreateTime
    )

    val ascendingTypeEntries: List<AscendingType> = listOf(
        AscendingType.Ascending, AscendingType.Descending
    )

    val viewTypeEntries: List<ViewType> = listOf(
        ViewType.List, ViewType.Grid
    )

}