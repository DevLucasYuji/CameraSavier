package com.ujizin.leafy

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setupContent()
    }

    private fun setupContent() {
        setContent {
            AppConfiguration {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                BackHandler(
                    navController = navController,
                    drawerState = drawerState,
                    onFinish = ::finish,
                )

                LeafyNavigation(navController = navController, drawerState = drawerState)
            }
        }
    }
}
