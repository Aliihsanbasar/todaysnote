package com.jotquill.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jotquill.screens.AddNoteScreen
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

        composable(JotQuillScreens.HomeScreen.name) { entry ->

            HomeScreen(navController, entry.savedStateHandle.get<Boolean>("isSaved"))
        }

        composable(JotQuillScreens.AddNoteScreen.name + "/{noteType}",
            arguments = listOf(navArgument("noteType") { type = NavType.StringType })) {

            AddNoteScreen(navController, it.arguments?.getString("noteType").toString())
        }
    }
}