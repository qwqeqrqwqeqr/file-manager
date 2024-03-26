package com.gradation.databox.feature.directory.naivgation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.databox.core.ui.compose.showImmediatelySnackbar
import com.gradation.databox.core.ui.event.EventState
import com.gradation.databox.core.ui.navigation.Route.HOME_ROUTE
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.model.PathTree
import com.gradation.databox.data.file.utils.toDataboxFileTypeList
import com.gradation.databox.feature.directory.data.DirectorySharedViewModel
import com.gradation.databox.feature.directory.data.DirectoryViewModel
import com.gradation.databox.feature.directory.data.model.AscendingType
import com.gradation.databox.feature.directory.data.model.SortType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.TypeState
import com.gradation.databox.feature.directory.data.state.rememberDirectoryScreenState
import com.gradation.databox.feature.directory.ui.DirectoryScreen
import com.gradation.databox.feature.directory.ui.bottomSheet.InfoBottomSheetView
import com.gradation.databox.feature.directory.ui.bottomSheet.SortBottomSheetView
import com.gradation.databox.feature.directory.ui.bottomSheet.ViewBottomSheetView
import com.gradation.databox.feature.directory.ui.dialog.CreateDirectoryDialog


@Composable
fun DirectoryRoute(
    modifier: Modifier,
    navController: NavController,
    navigateDirectoryToDirectory: (String) -> Unit,
    popBackStack: () -> Unit,
    viewModel: DirectoryViewModel = hiltViewModel(),
    sharedViewModel: DirectorySharedViewModel = hiltViewModel(
        navController.getBackStackEntry(
            HOME_ROUTE
        )
    ),
    directoryScreenState: DirectoryScreenState = rememberDirectoryScreenState(),
    fileState: FileState = sharedViewModel.fileState,
    typeState: TypeState = sharedViewModel.typeState
) {

    val directoryPath: String by viewModel.directoryPath.collectAsStateWithLifecycle()
    val pathTreeList: List<PathTree> by viewModel.pathTreeList.collectAsStateWithLifecycle()


    val eventState: EventState by sharedViewModel.eventState.collectAsStateWithLifecycle()
    val updateEventState: (EventState) -> Unit = sharedViewModel.updateEventState

    val fileList: List<DataboxFileType> =
        directoryPath.toDataboxFileTypeList().let { list ->
            when (typeState.ascendingType) {
                AscendingType.Ascending ->
                    when (typeState.sortType) {
                        is SortType.CreateTime -> list.sortedBy { it.creationTime }
                        is SortType.LastModifiedTime -> list.sortedBy { it.lastModifiedTime }
                        is SortType.Name -> list.sortedBy { it.name }
                        is SortType.Size -> list.sortedBy { it.size }
                    }

                AscendingType.Descending ->
                    when (typeState.sortType) {
                        is SortType.CreateTime -> list.sortedByDescending { it.creationTime }
                        is SortType.LastModifiedTime -> list.sortedByDescending { it.lastModifiedTime }
                        is SortType.Name -> list.sortedByDescending { it.name }
                        is SortType.Size -> list.sortedByDescending { it.size }
                    }
            }
        }

    LaunchedEffect(eventState) {
        when (val result = eventState) {
            is EventState.Fail -> {
                directoryScreenState.snackbarHostState.showImmediatelySnackbar(result.message)
                updateEventState(EventState.None)
            }

            EventState.None -> {}
            is EventState.Success -> {
                result.message?.let { message ->
                    directoryScreenState.snackbarHostState.showImmediatelySnackbar(message)
                }
                updateEventState(EventState.None)
            }
        }
    }



    if (directoryScreenState.sortBottomSheetView)
        SortBottomSheetView(
            modifier = modifier,
            typeState = typeState,
            sortTypeEntries = viewModel.getSortTypeEntries(),
            directoryScreenState = directoryScreenState,
        )


    if (directoryScreenState.viewBottomSheetView)
        ViewBottomSheetView(
            modifier = modifier,
            typeState = typeState,
            directoryScreenState = directoryScreenState,
        )


    if (directoryScreenState.infoBottomSheetView)
        InfoBottomSheetView(
            modifier = modifier,
            typeState = typeState,
            directoryScreenState = directoryScreenState,
        )

    if (directoryScreenState.createDirectoryDialogView)
        CreateDirectoryDialog(
            modifier = modifier,
            directoryPath = directoryPath,
            fileState = fileState,
            directoryScreenState = directoryScreenState,
        )



    DirectoryScreen(
        modifier,
        directoryPath,
        fileList,
        pathTreeList,
        fileState,
        typeState,
        popBackStack,
        navigateDirectoryToDirectory,
        directoryScreenState
    )

}
