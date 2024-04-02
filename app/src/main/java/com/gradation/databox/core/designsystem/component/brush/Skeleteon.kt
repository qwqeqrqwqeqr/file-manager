package com.gradation.databox.core.designsystem.component.brush

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.gradation.databox.core.designsystem.theme.DataboxTheme


@Composable
fun skeletonBrush(targetValue: Float = 1000f): Brush {

    val shimmerColors = listOf(
        DataboxTheme.colorScheme.surfaceColor.copy(alpha = 0.5f),
        DataboxTheme.colorScheme.surfaceColor.copy(alpha = 0.45f),
        DataboxTheme.colorScheme.surfaceColor.copy(alpha = 0.5f),
    )

    val skeletonTransition = rememberInfiniteTransition(label = "skeletonTransition")
    val skeletonTranslateAnimation = skeletonTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(1500), repeatMode = RepeatMode.Reverse
        ), label = "skeletonTransition"
    )
    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = Offset.Zero.x, y = Offset.Zero.y),
        end = Offset(x = skeletonTranslateAnimation.value, y = skeletonTranslateAnimation.value)
    )
}
