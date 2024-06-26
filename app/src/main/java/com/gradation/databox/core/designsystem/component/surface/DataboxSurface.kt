package com.gradation.databox.core.designsystem.component.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import com.gradation.databox.core.designsystem.theme.DataboxTheme

enum class SurfaceType { Primary, Secondary }

@Composable
fun DataboxSurface(
    modifier: Modifier = Modifier,
    surfaceType: SurfaceType = SurfaceType.Primary,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = DataboxTheme.space.space20,
        vertical = DataboxTheme.space.space20
    ),
    shape: RoundedCornerShape = RoundedCornerShape(DataboxTheme.space.space6),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                when (surfaceType) {
                    SurfaceType.Primary -> DataboxTheme.colorScheme.mainSurfaceColor
                    SurfaceType.Secondary -> DataboxTheme.colorScheme.surfaceColor
                },
                shape
            )
            .padding(paddingValues),
        content = content
    )
}


@PreviewLightDark
@Composable
fun DataboxSurfacePreview(modifier: Modifier = Modifier) {
    DataBoxTheme {
        Column {


            Column(
                modifier = modifier
                    .background(DataboxTheme.colorScheme.mainBackgroundColor)
                    .padding(DataboxTheme.space.space20),
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
            ) {
                DataboxSurface(
                    modifier
                        .fillMaxWidth()
                        .height(DataboxTheme.space.space32),
                    surfaceType = SurfaceType.Primary,
                    content = {})
            }

            Column(
                modifier = modifier
                    .background(DataboxTheme.colorScheme.backgroundColor)
                    .padding(DataboxTheme.space.space20),
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
            ) {
                DataboxSurface(
                    modifier
                        .fillMaxWidth()
                        .height(DataboxTheme.space.space32),
                    surfaceType = SurfaceType.Secondary,
                    content = {})
            }
        }
    }
}