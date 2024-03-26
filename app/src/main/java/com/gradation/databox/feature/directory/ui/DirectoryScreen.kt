package com.gradation.databox.feature.directory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.model.PathTree
import com.gradation.databox.feature.directory.data.model.ModeType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.TypeState
import com.gradation.databox.feature.directory.ui.bottomBar.CopyBottomBar
import com.gradation.databox.feature.directory.ui.bottomBar.EditBottomBar
import com.gradation.databox.feature.directory.ui.component.directory.DirectoryView
import com.gradation.databox.feature.directory.ui.component.header.HeaderView


@Composable
fun DirectoryScreen(
    modifier: Modifier,
    directoryPath: String,
    fileList: List<DataboxFileType>,
    pathTreeList: List<PathTree>,
    fileState: FileState,
    typeState: TypeState,
    popBackStack: () -> Unit,
    navigateDirectoryToDirectory: (String) -> Unit,
    directoryScreenState: DirectoryScreenState,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            when (val type = typeState.modeType) {
                is ModeType.COPY -> CopyBottomBar(
                    modifier,
                    directoryPath,
                    fileState,
                    typeState,
                    type
                )

                ModeType.Edit -> EditBottomBar(modifier, fileState, typeState)
                ModeType.View -> {}
            }


        },
        topBar = {
            HeaderView(
                modifier,
                pathTreeList,
                popBackStack,
                navigateDirectoryToDirectory,
                directoryScreenState
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DataboxTheme.colorScheme.backgroundColor)
                .padding(it)
        ) {
            DirectoryView(
                modifier,
                fileList,
                typeState,
                navigateDirectoryToDirectory,
                directoryScreenState
            )
        }
    }
}