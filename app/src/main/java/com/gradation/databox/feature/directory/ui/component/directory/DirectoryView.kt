package com.gradation.databox.feature.directory.ui.component.directory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.feature.directory.data.model.ViewType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.TypeState
import com.gradation.databox.feature.directory.ui.component.directory.grid.DirectoryGridView
import com.gradation.databox.feature.directory.ui.component.directory.list.DirectoryListView

@Composable
fun DirectoryView(
    modifier: Modifier = Modifier,
    fileList: List<DataboxFileType>,
    typeState: TypeState,
    navigateDirectoryToDirectory: (String) -> Unit,
    directoryScreenState: DirectoryScreenState,
) {
    if (fileList.isEmpty())
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier,
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier.size(DataboxTheme.space.space68),
                    imageVector = Icons.Filled.Folder,
                    contentDescription = "Folder",
                    tint = DataboxTheme.colorScheme.primaryIconColor
                )
                DataboxText(
                    textStyle = DataboxTextStyle.No3,
                    text = "폴더가 비어있습니다",
                    color = DataboxTheme.colorScheme.secondaryTextColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    else {
        when (typeState.viewType) {
            ViewType.Grid ->
                DirectoryGridView(
                    modifier,
                    fileList,
                    navigateDirectoryToDirectory,
                    directoryScreenState
                )

            ViewType.List -> DirectoryListView(
                modifier,
                fileList,
                navigateDirectoryToDirectory,
                directoryScreenState
            )
        }
    }
}