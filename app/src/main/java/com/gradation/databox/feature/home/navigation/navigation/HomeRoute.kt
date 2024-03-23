package com.gradation.databox.feature.home.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.databox.feature.home.navigation.ui.HomeScreen

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    navigateHomeToDirectory: (String) -> Unit,
) {


    HomeScreen(
        modifier,
        navigateHomeToDirectory
    )
}