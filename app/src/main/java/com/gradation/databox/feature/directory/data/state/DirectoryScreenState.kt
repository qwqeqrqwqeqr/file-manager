package com.gradation.databox.feature.directory.data.state


import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberDirectoryScreenState(
    lazyListState :LazyListState= rememberLazyListState()

): DirectoryScreenState {
    return remember(lazyListState) {
        DirectoryScreenState(lazyListState)
    }
}

class DirectoryScreenState(
    val lazyListState:LazyListState
) {

    var sortBottomSheetView: Boolean by mutableStateOf(false)
    var viewBottomSheetView: Boolean by mutableStateOf(false)


    val updateSortBottomSheetView: (Boolean) -> Unit = { sortBottomSheetView = it }
    val updateViewBottomSheetView: (Boolean) -> Unit = { viewBottomSheetView = it }


}