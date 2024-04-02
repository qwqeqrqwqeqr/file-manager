package com.gradation.databox.feature.directory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.domain.model.file.PathTree
import com.gradation.databox.domain.model.type.ViewType
import com.gradation.databox.feature.directory.data.model.ModeType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.DirectoryUiState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.ModeState
import com.gradation.databox.feature.directory.data.state.TypeState
import com.gradation.databox.feature.directory.ui.bottomBar.CopyBottomBar
import com.gradation.databox.feature.directory.ui.bottomBar.EditBottomBar
import com.gradation.databox.feature.directory.ui.component.directory.DirectoryView
import com.gradation.databox.feature.directory.ui.component.header.HeaderView


@Composable
fun DirectoryScreen(
    modifier: Modifier,
    directoryPath: String,
    directoryUiState: DirectoryUiState,
    pathTreeList: List<PathTree>,
    viewType: ViewType,
    fileState: FileState,
    typeState: TypeState,
    modeState: ModeState,
    popBackStack: () -> Unit,
    navigateDirectoryToDirectory: (String) -> Unit,
    directoryScreenState: DirectoryScreenState,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DataboxTheme.colorScheme.backgroundColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier.weight(1f)
        ) {
            HeaderView(
                modifier,
                pathTreeList,
                popBackStack,
                navigateDirectoryToDirectory,
                directoryScreenState
            )


            DirectoryView(
                modifier,
                viewType,
                directoryUiState,
                fileState,
                modeState,
                navigateDirectoryToDirectory,
                directoryScreenState
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            when (val type = modeState.modeType) {
                is ModeType.Copy ->
                    CopyBottomBar(
                        modifier,
                        directoryPath,
                        fileState,
                        modeState,
                        type
                    )


                ModeType.Edit -> EditBottomBar(modifier, fileState, modeState)


                ModeType.View -> {

                }
            }
        }
    }

}