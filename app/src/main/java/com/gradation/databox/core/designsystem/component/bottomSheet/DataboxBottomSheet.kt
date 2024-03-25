package com.gradation.databox.core.designsystem.component.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.SecureFlagPolicy
import com.gradation.databox.core.designsystem.theme.DataboxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataboxBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    containerColor: Color = DataboxTheme.colorScheme.backgroundColor,
    contentColor: Color = DataboxTheme.colorScheme.primaryTextColor,
    tonalElevation: Dp = BottomSheetDefaults.Elevation,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        shape = RoundedCornerShape(
            topStart = DataboxTheme.space.space16,
            topEnd = DataboxTheme.space.space16
        ),
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        dragHandle = dragHandle,
        onDismissRequest = onDismissRequest,
        properties = ModalBottomSheetProperties(
            SecureFlagPolicy.Inherit,
            isFocusable = true,
            shouldDismissOnBackPress = true
        ),
        windowInsets = WindowInsets.displayCutout,
    ) {
        Column(
            modifier = modifier.navigationBarsPadding(),
            content = content
        )
    }
}

