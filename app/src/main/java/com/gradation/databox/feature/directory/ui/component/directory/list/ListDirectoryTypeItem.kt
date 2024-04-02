package com.gradation.databox.feature.directory.ui.component.directory.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.databox.core.designsystem.component.checkbox.DataboxCheckbox
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.noRippleClickable
import com.gradation.databox.domain.mapper.toText
import com.gradation.databox.domain.model.file.FileType
import com.gradation.databox.feature.directory.data.model.ModeType
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.ModeState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListDirectoryTypeItem(
    modifier: Modifier = Modifier,
    file: FileType.DirectoryType,
    fileState: FileState,
    modeState: ModeState,
    navigateDirectoryToDirectory: (String) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .combinedClickable(
                    onLongClick = {
                        modeState.updateModeType(ModeType.Edit)
                        fileState.selectFile(file.absolutePath)
                    },
                    onDoubleClick = null,
                    onClick = {
                        navigateDirectoryToDirectory(file.absolutePath)
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                )
                .padding(vertical = DataboxTheme.space.space12),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
            ) {
                AnimatedVisibility(visible = modeState.modeType is ModeType.Edit) {
                    DataboxCheckbox(
                        modifier = modifier,
                        checked = fileState.selectedFileList.contains(file.absolutePath),
                        onCheckedChange = {
                            if (fileState.selectedFileList.contains(file.absolutePath)) fileState.unselectFile(
                                file.absolutePath
                            )
                            else fileState.selectFile(file.absolutePath)
                        }
                    )
                }
                Icon(
                    modifier = modifier.size(DataboxTheme.space.space28),
                    imageVector = Icons.Filled.Folder,
                    contentDescription = "Folder",
                    tint = DataboxTheme.colorScheme.primaryIconColor
                )
                Column(verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4)) {
                    DataboxText(
                        textStyle = DataboxTextStyle.No5,
                        text = file.name,
                        color = DataboxTheme.colorScheme.primaryTextColor,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4)
                    ) {
                        DataboxText(
                            textStyle = DataboxTextStyle.No7,
                            text = file.lastModifiedTime.toText(),
                            color = DataboxTheme.colorScheme.tertiaryTextColor,
                            textAlign = TextAlign.Start
                        )
                        DataboxText(
                            textStyle = DataboxTextStyle.No7,
                            text = "하위항목 ${file.itemSize} 개",
                            color = DataboxTheme.colorScheme.tertiaryTextColor,
                            textAlign = TextAlign.Start
                        )
                        DataboxText(
                            textStyle = DataboxTextStyle.No7,
                            text = file.size.takeIf { it != 0L }?.toText() ?: "",
                            color = DataboxTheme.colorScheme.tertiaryTextColor,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = "ChevronRight",
                tint = DataboxTheme.colorScheme.primaryIconColor
            )
        }
    }
    HorizontalDivider(
        modifier = modifier,
        color = DataboxTheme.colorScheme.dividerColor,
        thickness = 0.5.dp
    )
}