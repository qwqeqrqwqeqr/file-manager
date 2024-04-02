package com.gradation.databox.feature.directory.ui.component.directory.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.domain.mapper.toText
import com.gradation.databox.domain.model.file.FileType

@Composable
fun GridImageTypeItem(
    modifier: Modifier=Modifier,
    file: FileType.ImageType,
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4)
    ) {
        AsyncImage(
            modifier = modifier
                .size(DataboxTheme.space.space44)
                .clip(RoundedCornerShape(DataboxTheme.space.space4)),
            model = file.absolutePath,
            contentDescription = file.absolutePath,
            contentScale = ContentScale.FillBounds,
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