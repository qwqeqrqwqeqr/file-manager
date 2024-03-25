package com.gradation.databox.feature.directory.ui.component.directory.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.utils.mapper.toText
import com.gradation.databox.data.file.model.DataboxFileType

@Composable
fun GridFileTypeItem(
    modifier: Modifier=Modifier,
    file: DataboxFileType.FileType,
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
    ) {
        Icon(
            modifier = modifier.size(DataboxTheme.space.space40),
            imageVector = Icons.Outlined.Description,
            contentDescription = "Description",
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
                text = file.lastModifiedTime.toText(),
                color = DataboxTheme.colorScheme.tertiaryTextColor,
                textAlign = TextAlign.Start
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