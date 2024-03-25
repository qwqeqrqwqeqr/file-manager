package com.gradation.databox.feature.directory.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gradation.databox.feature.directory.data.model.AscendingType
import com.gradation.databox.feature.directory.data.model.SortType
import com.gradation.databox.feature.directory.data.model.ViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DirectorySharedViewModel @Inject constructor(
) : ViewModel() {


    val sortType: MutableState<SortType> = mutableStateOf(SortType.Name())
    val viewType: MutableState<ViewType> = mutableStateOf(ViewType.Grid)
    val ascendingType: MutableState<AscendingType> = mutableStateOf(AscendingType.Ascending)



    val updateSortType: (SortType) -> Unit = { type ->
        sortType.value = type
    }
    val updateAscendingType: (AscendingType) -> Unit = { ascendingType.value = it }

    val updateViewType: (ViewType) -> Unit = { viewType.value = it }


}