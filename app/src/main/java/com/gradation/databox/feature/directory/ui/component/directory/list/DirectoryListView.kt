package com.gradation.databox.feature.directory.ui.component.directory.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.scrollBar
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState

@Composable
fun DirectoryListView(
    modifier: Modifier = Modifier,
    fileList: List<DataboxFileType>,
    navigateDirectoryToDirectory: (String) -> Unit,
    directoryScreenState: DirectoryScreenState,
) {
    LazyColumn(
        modifier
            .scrollBar(directoryScreenState.lazyListState)
            .padding(horizontal = DataboxTheme.space.space20),
        state = directoryScreenState.lazyListState
    ) {
        items(fileList, key = { file -> file.absolutePath }) { file ->
            when (file) {
                is DataboxFileType.DirectoryType ->
                    ListDirectoryTypeItem(modifier, file, navigateDirectoryToDirectory)

                is DataboxFileType.FileType ->
                    ListFileTypeItem(modifier, file)

                is DataboxFileType.ImageType ->
                    ListImageFileTypeItem(modifier, file)
            }
        }
    }
}