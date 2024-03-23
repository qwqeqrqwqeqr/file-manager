package com.gradation.databox.feature.directory.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.utils.mapper.toText
import com.gradation.databox.data.file.model.DataboxFileType

@Composable
fun ImageFileTypeItem(
    modifier: Modifier = Modifier,
    file: DataboxFileType.ImageType
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = DataboxTheme.space.space8),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
            ) {
                AsyncImage(
                    modifier = modifier.size(DataboxTheme.space.space36).clip(RoundedCornerShape(DataboxTheme.space.space4)),
                    model = file.absolutePath,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
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
                            text = file.size.toText(),
                            color = DataboxTheme.colorScheme.tertiaryTextColor,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
        HorizontalDivider(
            modifier = modifier,
            color = DataboxTheme.colorScheme.dividerColor,
            thickness = 0.5.dp
        )
    }
}