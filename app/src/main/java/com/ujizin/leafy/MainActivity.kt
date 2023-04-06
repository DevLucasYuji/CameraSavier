package com.ujizin.leafy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ujizin.leafy.alarm.AlarmService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.action == AlarmService.STOP_ACTION) {
            stopService(Intent(this, AlarmService::class.java))
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setupContent()
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun setupContent() {
        setContent {
            AppConfiguration {
                val navController = rememberAnimatedNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                LeafyNavigation(
                    navController = navController,
                    drawerState = drawerState,
                    onBackPressed = { scope.launch { navController.navigateUp(drawerState) } },
                )
            }
        }
    }

    private suspend fun NavController.navigateUp(drawerState: DrawerState) {
        if (drawerState.isOpen) {
            drawerState.close()
            return
        }

        if (!navigateUp()) finish()
    }
}
