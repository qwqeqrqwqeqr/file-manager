package com.gradation.databox.feature.directory.ui.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.noRippleClickable
import com.gradation.databox.core.utils.mapper.toText
import com.gradation.databox.data.file.model.DataboxFileType

@Composable
fun DirectoryTypeItem(
    modifier: Modifier = Modifier,
    file: DataboxFileType.DirectoryType,
    navigateDirectoryToDirectory: (String) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .noRippleClickable {
                    navigateDirectoryToDirectory(file.absolutePath)
                }
                .padding(vertical = DataboxTheme.space.space12),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
            ) {
                Icon(
                    modifier = modifier.size(DataboxTheme.space.space28),
                    imageVector = Icons.Filled.Folder,
                    contentDescription = "Folder",
                    tint = DataboxTheme.colorScheme.iconColor
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
                    }
                }
            }
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = "ChevronRight",
                tint = DataboxTheme.colorScheme.iconColor
            )
        }
    }
    HorizontalDivider(
        modifier = modifier,
        color = DataboxTheme.colorScheme.dividerColor,
        thickness = 0.5.dp
    )
}