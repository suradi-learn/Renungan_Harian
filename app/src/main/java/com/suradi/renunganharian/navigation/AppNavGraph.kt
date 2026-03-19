package com.suradi.renunganharian.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suradi.renunganharian.data.dummy.dummyDevotionals
import com.suradi.renunganharian.ui.screen.DetailScreen
import com.suradi.renunganharian.ui.screen.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onClickDetail = {
                    navController.navigate("detail")
                }
            )
        }

        composable("detail") {
            DetailScreen(
                devotional = dummyDevotionals[0],
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}