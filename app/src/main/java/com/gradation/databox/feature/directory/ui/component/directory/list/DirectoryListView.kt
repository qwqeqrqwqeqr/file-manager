package com.gradation.databox.feature.directory.ui.component.directory.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.scrollBar
import com.gradation.databox.domain.model.file.FileType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.ModeState

@Composable
fun DirectoryListView(
    modifier: Modifier = Modifier,
    fileList: List<FileType>,
    fileState: FileState,
    modeState: ModeState,
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
                is FileType.DirectoryType ->
                    ListDirectoryTypeItem(modifier, file, fileState, modeState, navigateDirectoryToDirectory)

                is FileType.DefaultFileType ->
                    ListFileTypeItem(modifier, file,fileState,modeState)

                is FileType.ImageType ->
                    ListImageFileTypeItem(modifier, file,fileState,modeState)
            }
        }
    }
}