package com.gradation.databox.core.designsystem.component.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import com.gradation.databox.core.designsystem.theme.DataboxTheme


@Composable
fun DataboxSurface(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(
                DataboxTheme.colorScheme.backgroundColor,
                RoundedCornerShape(DataboxTheme.space.space6)
            )
            .padding(
                horizontal = DataboxTheme.space.space12,
                vertical = DataboxTheme.space.space8
            ),
        content = content
    )
}


@PreviewLightDark
@Composable
fun DataboxSurfacePreview(modifier: Modifier = Modifier) {
    DataBoxTheme {
        Column(
            modifier = modifier
                .background(DataboxTheme.colorScheme.mainBackgroundColor)
                .padding(DataboxTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
        ) {
            DataboxSurface(
                modifier
                    .fillMaxWidth()
                    .height(DataboxTheme.space.space32))
            {}
        }
    }
}