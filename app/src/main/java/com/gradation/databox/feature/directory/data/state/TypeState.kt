package com.gradation.databox.feature.directory.data.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.gradation.databox.feature.directory.data.model.AscendingType
import com.gradation.databox.feature.directory.data.model.ModeType
import com.gradation.databox.feature.directory.data.model.SortType
import com.gradation.databox.feature.directory.data.model.ViewType

class TypeState {
    var sortType: SortType by mutableStateOf(SortType.Name())
    var viewType: ViewType by mutableStateOf(ViewType.List)
    var ascendingType: AscendingType by mutableStateOf(AscendingType.Ascending)
    var modeType: ModeType by mutableStateOf(ModeType.View)

    val updateSortType: (SortType) -> Unit = { type ->
        sortType = type
    }
    val updateAscendingType: (AscendingType) -> Unit = { ascendingType = it }

    val updateViewType: (ViewType) -> Unit = { viewType = it }

    val updateModeType: (ModeType) -> Unit = { modeType = it }


}