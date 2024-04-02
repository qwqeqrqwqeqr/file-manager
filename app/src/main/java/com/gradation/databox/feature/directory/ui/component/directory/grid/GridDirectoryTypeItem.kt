package com.gradation.databox.feature.directory.ui.component.directory.grid

import androidx.compose.foundation.layout.Column
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
import com.gradation.databox.core.ui.compose.noRippleClickable
import com.gradation.databox.domain.mapper.toText
import com.gradation.databox.domain.model.file.FileType

@Composable
fun GridDirectoryTypeItem(
    modifier:Modifier,
    file: FileType.DirectoryType,
    navigateDirectoryToDirectory: (String) -> Unit,
){
    Column(
        modifier = modifier.noRippleClickable {
            navigateDirectoryToDirectory(file.absolutePath)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier.size(DataboxTheme.space.space48),
            imageVector = Icons.Filled.Folder,
            contentDescription = "Folder",
            tint = DataboxTheme.colorScheme.primaryIconColor
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DataboxText(
                textStyle = DataboxTextStyle.No9,
                text = file.name,
                color = DataboxTheme.colorScheme.primaryTextColor,
                textAlign = TextAlign.Center
            )
            DataboxText(
                textStyle = DataboxTextStyle.No10,
                text = "하위항목 ${file.itemSize} 개",
                color = DataboxTheme.colorScheme.tertiaryTextColor,
                textAlign = TextAlign.Center
            )
            DataboxText(
                textStyle = DataboxTextStyle.No10,
                text = file.size.takeIf { it != 0L }?.toText() ?: "",
                color = DataboxTheme.colorScheme.tertiaryTextColor,
                textAlign = TextAlign.Center
            )
        }
    }
}