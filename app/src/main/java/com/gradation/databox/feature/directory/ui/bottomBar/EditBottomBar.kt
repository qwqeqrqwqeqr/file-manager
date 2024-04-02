package com.gradation.databox.feature.directory.ui.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DriveFileMove
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FolderCopy
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.noRippleClickable
import com.gradation.databox.feature.directory.data.model.BottomBarItem
import com.gradation.databox.feature.directory.data.model.ModeType
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.ModeState

@Composable
fun EditBottomBar(
    modifier: Modifier,
    fileState: FileState,
    modeState: ModeState,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(DataboxTheme.colorScheme.surfaceColor)
            .padding(
                horizontal = DataboxTheme.space.space20,
                vertical = DataboxTheme.space.space12
            ),
        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf(
            BottomBarItem("취소", Icons.Filled.Close, {
                fileState.clear()
                modeState.updateModeType(ModeType.View)
            }, true),
            BottomBarItem(
                "이동",
                Icons.AutoMirrored.Filled.DriveFileMove,
                {
                    if (fileState.selectedFileList.isNotEmpty())
                        modeState.updateModeType(ModeType.Copy(isCopy = false))
                },
                fileState.selectedFileList.isNotEmpty()
            ),
            BottomBarItem(
                "복사",
                Icons.Filled.FolderCopy,
                {
                    if (fileState.selectedFileList.isNotEmpty())
                        modeState.updateModeType(ModeType.Copy(isCopy = true))
                },
                fileState.selectedFileList.isNotEmpty()
            ),
            BottomBarItem(
                "삭제",
                Icons.Filled.Delete,
                {
                    if (fileState.selectedFileList.isNotEmpty()) {
                        fileState.deleteFile()
                        modeState.updateModeType(ModeType.View)
                    }
                },
                fileState.selectedFileList.isNotEmpty()
            ),
            BottomBarItem("더보기", Icons.Filled.MoreHoriz, {}, true),
        ).forEach {
            Column(
                modifier = modifier
                    .weight(1f)
                    .noRippleClickable(onClick = it.onClick),
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space2),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier.size(DataboxTheme.space.space24),
                    imageVector = it.icon,
                    contentDescription = it.icon.toString(),
                    tint =
                    if (it.enabled) DataboxTheme.colorScheme.secondaryIconColor
                    else DataboxTheme.colorScheme.primaryIconColor
                )
                DataboxText(
                    textStyle = DataboxTextStyle.No7,
                    text = it.name,
                    color = DataboxTheme.colorScheme.primaryTextColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}