package com.gradation.databox.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.gradation.databox.theme.DataBoxTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        setContent {
            DataBoxTheme {
                LiftApp(
                    modifier = Modifier,
                    appState = rememberAppState(),
                )
            }
        }
    }
}

