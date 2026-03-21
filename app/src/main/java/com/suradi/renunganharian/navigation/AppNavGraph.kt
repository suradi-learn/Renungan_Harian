package com.suradi.renunganharian.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suradi.renunganharian.data.dummy.dummyDevotionals
import com.suradi.renunganharian.ui.screen.DetailScreen
import com.suradi.renunganharian.ui.screen.FavoriteScreen
import com.suradi.renunganharian.ui.screen.HomeScreen
import com.suradi.renunganharian.viewmodel.FavoriteViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val favoriteViewModel: FavoriteViewModel = viewModel()

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
                },
                onFavoriteClick = {
                    navController.navigate("favorite")
                },
                favoriteViewModel = favoriteViewModel
            )
        }

        composable("favorite") {
            FavoriteScreen(
                favoriteViewModel = favoriteViewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onItemClick = { devotional ->
                    navController.navigate("detail")
                }
            )
        }
    }
}