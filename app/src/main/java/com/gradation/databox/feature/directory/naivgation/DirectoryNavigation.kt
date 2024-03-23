package com.gradation.databox.feature.directory.naivgation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.databox.core.ui.navigation.Key.DIRECTORY_KEY
import com.gradation.databox.core.ui.navigation.Route.DIRECTORY_ROUTE
import com.gradation.databox.core.ui.navigation.navigateDirectoryToDirectory

fun NavGraphBuilder.directoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navigateDirectoryToDirectory: (String) -> Unit =
        { navController.navigateDirectoryToDirectory(it) }

    composable(
        route = "$DIRECTORY_ROUTE?$DIRECTORY_KEY={$DIRECTORY_KEY}",
        arguments = listOf(
            navArgument(DIRECTORY_KEY) {
                type = NavType.StringType
            }
        )) {

        DirectoryRoute(
            modifier, navigateDirectoryToDirectory
        )

    }
}