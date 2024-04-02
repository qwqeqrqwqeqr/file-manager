package com.gradation.databox.feature.directory.ui.component.directory.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.scrollBar
import com.gradation.databox.domain.model.file.FileType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.ModeState

@Composable
fun DirectoryGridView(
    modifier: Modifier = Modifier,
    fileList: List<FileType>,
    fileState: FileState,
    modeState: ModeState,
    navigateDirectoryToDirectory: (String) -> Unit,
    directoryScreenState: DirectoryScreenState
) {
    LazyVerticalGrid(
        state = directoryScreenState.lazyGridState,
        modifier = modifier
            .scrollBar(directoryScreenState.lazyGridState)
            .padding(horizontal = DataboxTheme.space.space6),
        columns = GridCells.Fixed(5),
        verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4),
        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space2)
    ) {
        items(fileList, key = { file -> file.absolutePath }) { file ->
            when (file) {
                is FileType.DirectoryType -> GridDirectoryTypeItem(modifier, file, navigateDirectoryToDirectory)

                is FileType.DefaultFileType -> GridFileTypeItem(modifier, file)
                is FileType.ImageType -> GridImageTypeItem(modifier, file)
            }
        }
    }
}