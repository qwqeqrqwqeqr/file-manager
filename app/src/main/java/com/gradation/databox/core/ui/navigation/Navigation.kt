package com.gradation.databox.core.ui.navigation

import androidx.navigation.NavController
import com.gradation.databox.core.ui.navigation.Key.DIRECTORY_KEY
import com.gradation.databox.core.ui.navigation.Route.DIRECTORY_ROUTE


fun NavController.navigateHomeToDirectory(path: String) {
    this.navigate("$DIRECTORY_ROUTE?$DIRECTORY_KEY=$path")


}


fun NavController.navigateDirectoryToDirectory(path: String) {
    this.navigate("$DIRECTORY_ROUTE?$DIRECTORY_KEY=$path")
}