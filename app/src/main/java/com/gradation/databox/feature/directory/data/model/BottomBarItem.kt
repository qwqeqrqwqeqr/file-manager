package com.gradation.databox.feature.directory.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val name: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
    val enabled:Boolean
)
