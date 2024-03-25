package com.gradation.databox.feature.directory.data.state


import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import com.gradation.databox.core.ui.compose.LocalSnackbarHostState

@Composable
fun rememberDirectoryScreenState(
    lazyListState: LazyListState = rememberLazyListState(),
    lazyGridState: LazyGridState = rememberLazyGridState()

): DirectoryScreenState {
    val configuration = LocalConfiguration.current
    val snackbarHostState = LocalSnackbarHostState.current

    return remember(lazyListState, lazyGridState, configuration, snackbarHostState) {
        DirectoryScreenState(lazyListState, lazyGridState, configuration, snackbarHostState)
    }
}

class DirectoryScreenState(
    val lazyListState: LazyListState,
    val lazyGridState: LazyGridState,
    val configuration: Configuration,
    val snackbarHostState: SnackbarHostState
) {

    var sortBottomSheetView: Boolean by mutableStateOf(false)
    var viewBottomSheetView: Boolean by mutableStateOf(false)
    var infoBottomSheetView: Boolean by mutableStateOf(false)

    var createDirectoryDialogView: Boolean by mutableStateOf(false)

    val updateSortBottomSheetView: (Boolean) -> Unit = { sortBottomSheetView = it }
    val updateViewBottomSheetView: (Boolean) -> Unit = { viewBottomSheetView = it }
    val updateInfoBottomSheetView: (Boolean) -> Unit = { infoBottomSheetView = it }

    var updateCreateDirectoryDialogView: (Boolean) -> Unit = { createDirectoryDialogView = it }
}