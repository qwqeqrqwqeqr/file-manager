package com.gradation.databox.feature.directory.data.state


import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberDirectoryScreenState(
    lazyListState:LazyListState= rememberLazyListState(),
    lazyGridState: LazyGridState = rememberLazyGridState()

): DirectoryScreenState {
    return remember(lazyListState,lazyGridState) {
        DirectoryScreenState(lazyListState,lazyGridState)
    }
}

class DirectoryScreenState(
    val lazyListState:LazyListState,
    val lazyGridState: LazyGridState
) {

    var sortBottomSheetView: Boolean by mutableStateOf(false)
    var viewBottomSheetView: Boolean by mutableStateOf(false)


    val updateSortBottomSheetView: (Boolean) -> Unit = { sortBottomSheetView = it }
    val updateViewBottomSheetView: (Boolean) -> Unit = { viewBottomSheetView = it }


}