package com.gradation.databox.feature.directory.naivgation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.databox.core.ui.compose.showImmediatelySnackbar
import com.gradation.databox.core.common.state.EventState
import com.gradation.databox.core.ui.navigation.Route.HOME_ROUTE
import com.gradation.databox.domain.model.file.PathTree
import com.gradation.databox.domain.model.type.AscendingType
import com.gradation.databox.domain.model.type.SortType
import com.gradation.databox.domain.model.type.ViewType
import com.gradation.databox.feature.directory.data.DirectorySharedViewModel
import com.gradation.databox.feature.directory.data.DirectoryViewModel
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.DirectoryUiState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.ModeState
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
    currentPath:String,
    navController: NavController,
    navigateDirectoryToDirectory: (String) -> Unit,
    popBackStack: () -> Unit,
    viewModel: DirectoryViewModel = hiltViewModel(),
    sharedViewModel: DirectorySharedViewModel = hiltViewModel(
        navController.getBackStackEntry(
            HOME_ROUTE
        )
    ),
    fileState: FileState = sharedViewModel.fileState,
    modeState: ModeState = sharedViewModel.modeState,
    typeState: TypeState = viewModel.typeState,
    directoryScreenState: DirectoryScreenState = rememberDirectoryScreenState()
) {

    val directoryPath: String by sharedViewModel.directoryPath.collectAsStateWithLifecycle()
    val pathTreeList: List<PathTree> by viewModel.pathTreeList.collectAsStateWithLifecycle()
    val directoryUiState: DirectoryUiState by viewModel.directoryUiState.collectAsStateWithLifecycle()

    val eventState: EventState by sharedViewModel.eventState.collectAsStateWithLifecycle()
    val updateEventState: (EventState) -> Unit = sharedViewModel.updateEventState

    val sortType: SortType by typeState.sortType.collectAsStateWithLifecycle()
    val viewType: ViewType by typeState.viewType.collectAsStateWithLifecycle()
    val ascendingType: AscendingType by typeState.ascendingType.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        sharedViewModel.updateDirectoryPath(currentPath)
    }



    if (directoryScreenState.sortBottomSheetView)
        SortBottomSheetView(modifier, sortType, ascendingType, typeState, directoryScreenState)

    if (directoryScreenState.viewBottomSheetView)
        ViewBottomSheetView(modifier, viewType, typeState, directoryScreenState)

    if (directoryScreenState.infoBottomSheetView)
        InfoBottomSheetView(modifier, modeState, directoryScreenState)

    if (directoryScreenState.createDirectoryDialogView)
        CreateDirectoryDialog(modifier, directoryPath, fileState, directoryScreenState)



    DirectoryScreen(
        modifier,
        directoryPath,
        directoryUiState,
        pathTreeList,
        viewType,
        fileState,
        typeState,
        modeState,
        popBackStack,
        navigateDirectoryToDirectory,
        directoryScreenState
    )

}
