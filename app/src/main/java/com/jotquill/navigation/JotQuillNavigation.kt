package com.jotquill.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jotquill.screens.HomeScreen
import com.jotquill.screens.SplashScreen

@Composable
fun JotQuillNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = JotQuillScreens.SplashScreen.name
    ) {

        composable(JotQuillScreens.SplashScreen.name) {
            SplashScreen(navController)
        }

        composable(JotQuillScreens.HomeScreen.name) {
            HomeScreen(navController)
        }
    }
}