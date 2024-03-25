package com.gradation.databox.feature.directory.naivgation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.databox.core.ui.navigation.Route.HOME_ROUTE
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.model.PathTree
import com.gradation.databox.data.file.utils.toDataboxFileTypeList
import com.gradation.databox.feature.directory.data.DirectorySharedViewModel
import com.gradation.databox.feature.directory.data.DirectoryViewModel
import com.gradation.databox.feature.directory.data.model.AscendingType
import com.gradation.databox.feature.directory.data.model.SortType
import com.gradation.databox.feature.directory.data.model.ViewType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.rememberDirectoryScreenState
import com.gradation.databox.feature.directory.ui.DirectoryScreen
import com.gradation.databox.feature.directory.ui.bottomSheet.SortBottomSheetView
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


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
    directoryScreenState: DirectoryScreenState = rememberDirectoryScreenState()
) {

    val directoryPath: String by viewModel.directoryPath.collectAsStateWithLifecycle()
    val pathTreeList: List<PathTree> by viewModel.pathTreeList.collectAsStateWithLifecycle()


    val sortType: SortType by sharedViewModel.sortType
    val viewType: ViewType by sharedViewModel.viewType
    val ascendingType: AscendingType by sharedViewModel.ascendingType

    val fileList: List<DataboxFileType> =
        directoryPath.toDataboxFileTypeList().let { list ->
            when (ascendingType) {
                AscendingType.Ascending ->
                    when (sortType) {
                        is SortType.CreateTime -> list.sortedBy { it.creationTime }
                        is SortType.LastModifiedTime -> list.sortedBy { it.lastModifiedTime }
                        is SortType.Name -> list.sortedBy { it.name }
                        is SortType.Size -> list.sortedBy { it.size }
                    }

                AscendingType.Descending ->
                    when (sortType) {
                        is SortType.CreateTime -> list.sortedByDescending { it.creationTime }
                        is SortType.LastModifiedTime -> list.sortedByDescending { it.lastModifiedTime }
                        is SortType.Name -> list.sortedByDescending { it.name }
                        is SortType.Size -> list.sortedByDescending { it.size }
                    }
            }
        }



    if (directoryScreenState.sortBottomSheetView) {
        SortBottomSheetView(
            modifier = modifier,
            sortType = sortType,
            ascendingType = ascendingType,
            updateSortType = sharedViewModel.updateSortType,
            updateAscendingType = sharedViewModel.updateAscendingType,
            updateSortBottomSheetView = directoryScreenState.updateSortBottomSheetView,
            sortTypeEntries = viewModel.getSortTypeEntries()
        )
    }

    AnimatedVisibility(visible = directoryScreenState.viewBottomSheetView) {

    }


    DirectoryScreen(
        modifier,
        directoryPath,
        fileList,
        pathTreeList,
        popBackStack,
        navigateDirectoryToDirectory,
        directoryScreenState
    )

}
