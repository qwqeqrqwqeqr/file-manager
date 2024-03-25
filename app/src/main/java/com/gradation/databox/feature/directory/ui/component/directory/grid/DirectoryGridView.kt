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
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState

@Composable
fun DirectoryGridView(
    modifier: Modifier = Modifier,
    fileList: List<DataboxFileType>,
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
                is DataboxFileType.DirectoryType -> GridDirectoryTypeItem(
                    modifier,
                    file,
                    navigateDirectoryToDirectory
                )

                is DataboxFileType.FileType -> GridFileTypeItem(modifier, file)
                is DataboxFileType.ImageType -> GridImageTypeItem(modifier, file)
            }
        }
    }
}