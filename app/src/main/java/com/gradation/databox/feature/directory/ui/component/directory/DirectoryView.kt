package com.gradation.databox.feature.directory.ui.component.directory

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.databox.core.designsystem.component.brush.skeletonBrush
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.domain.model.type.ViewType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.DirectoryUiState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.ModeState
import com.gradation.databox.feature.directory.ui.component.directory.grid.DirectoryGridView
import com.gradation.databox.feature.directory.ui.component.directory.list.DirectoryListView

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DirectoryView(
    modifier: Modifier = Modifier,
    viewType: ViewType,
    directoryUiState: DirectoryUiState,
    fileState: FileState,
    modeState: ModeState,
    navigateDirectoryToDirectory: (String) -> Unit,
    directoryScreenState: DirectoryScreenState,
) {
    when (directoryUiState) {
        DirectoryUiState.Empty -> Box(
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

        DirectoryUiState.Loading -> {
            Column(
                modifier = modifier.padding(horizontal = DataboxTheme.space.space20),
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space24)
            ) {
                repeat(7) {
                    Spacer(
                        modifier = modifier.weight(1f)
                            .fillMaxWidth()
                            .background(
                                brush = skeletonBrush(),
                                shape = RoundedCornerShape(DataboxTheme.space.space12)
                            )

                    )
                }
            }
        }

        is DirectoryUiState.Success ->
            when (viewType) {
            ViewType.Grid ->
                DirectoryGridView(
                    modifier,
                    directoryUiState.fileList,
                    fileState,
                    modeState,
                    navigateDirectoryToDirectory,
                    directoryScreenState
                )

            ViewType.List -> DirectoryListView(
                modifier,
                directoryUiState.fileList,
                fileState,
                modeState,
                navigateDirectoryToDirectory,
                directoryScreenState
            )
        }
    }

}