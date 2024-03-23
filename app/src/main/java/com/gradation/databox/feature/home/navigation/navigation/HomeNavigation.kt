package com.gradation.databox.feature.home.navigation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.databox.core.ui.navigation.Route
import com.gradation.databox.core.ui.navigation.navigateHomeToDirectory

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateHomeToDirectory: (String) -> Unit = { navController.navigateHomeToDirectory(it) }

    composable(Route.HOME_ROUTE) {
        HomeRoute(
            modifier,
            navigateHomeToDirectory
        )
    }

}