package com.gradation.databox.core.ui.compose

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import com.gradation.databox.core.designsystem.theme.DataboxTheme

@SuppressLint("ModifierFactoryUnreferencedReceiver")
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = this.composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


@Composable
fun Modifier.scrollBar(
    lazyListState: LazyListState,
    width: Dp = DataboxTheme.space.space4,
    barColor: Color = DataboxTheme.colorScheme.primaryColor
): Modifier {

    val color: Color by animateColorAsState(
        targetValue = if (lazyListState.isScrollInProgress) barColor.copy(0.7f) else barColor.copy(
            0.1f
        ),
        tween(if (lazyListState.isScrollInProgress) 0 else 500),
        label = "barColorTransition"
    )

    return drawWithContent {
        drawContent()


        val firstVisibleElementIndex =
            lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.index

        if (firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / lazyListState.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
            val scrollbarHeight = lazyListState.layoutInfo.visibleItemsInfo.size * elementHeight

            drawLine(
                color = color,
                start = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                end = Offset(this.size.width - width.toPx(), scrollbarOffsetY + scrollbarHeight),
                strokeWidth = width.toPx(),
                cap = StrokeCap.Round,
            )
        }
    }
}


@Composable
fun Modifier.scrollBar(
    lazyGridState: LazyGridState,
    width: Dp = DataboxTheme.space.space4,
    barColor: Color = DataboxTheme.colorScheme.primaryColor
): Modifier {

    val color: Color by animateColorAsState(
        targetValue = if (lazyGridState.isScrollInProgress) barColor.copy(0.7f) else barColor.copy(
            0.1f
        ),
        tween(if (lazyGridState.isScrollInProgress) 0 else 500),
        label = "barColorTransition"
    )

    return drawWithContent {
        drawContent()


        val firstVisibleElementIndex =
            lazyGridState.layoutInfo.visibleItemsInfo.firstOrNull()?.index

        if (firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / lazyGridState.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
            val scrollbarHeight = lazyGridState.layoutInfo.visibleItemsInfo.size * elementHeight

            drawLine(
                color = color,
                start = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                end = Offset(this.size.width - width.toPx(), scrollbarOffsetY + scrollbarHeight),
                strokeWidth = width.toPx(),
                cap = StrokeCap.Round,
            )
        }
    }
}