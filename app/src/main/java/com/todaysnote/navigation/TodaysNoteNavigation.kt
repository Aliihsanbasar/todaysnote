package com.todaysnote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.todaysnote.screens.HomeScreen
import com.todaysnote.screens.SplashScreen

@Composable
fun TodaysNoteNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TodaysNoteScreens.SplashScreen.name
    ) {

        composable(TodaysNoteScreens.SplashScreen.name) {
            SplashScreen(navController)
        }

        composable(TodaysNoteScreens.HomeScreen.name) {
            HomeScreen(navController)
        }
    }
}