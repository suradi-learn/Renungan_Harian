package com.suradi.renunganharian.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suradi.renunganharian.ui.screen.DetailScreen
import com.suradi.renunganharian.ui.screen.FavoriteScreen
import com.suradi.renunganharian.ui.screen.HomeScreen
import com.suradi.renunganharian.ui.screen.PreviousDevotionsScreen
import com.suradi.renunganharian.viewmodel.FavoriteViewModel
import com.suradi.renunganharian.viewmodel.HomeViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val favoriteViewModel: FavoriteViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()

    val devotionals by homeViewModel.devotionals.collectAsState()


    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onClickDetail = { devotionalId ->
                    navController.navigate("detail/$devotionalId")
                },
                onClickPreviousDevotions = {
                    navController.navigate("previous_devotions")
                }
            )
        }

        composable("previous_devotions") {
            PreviousDevotionsScreen(
                devotionals = devotionals,
                onBackClick = {
                    navController.popBackStack()
                },
                onItemClick = { devotionalId ->
                    navController.navigate("detail/$devotionalId")
                }
            )
        }

        composable("detail/{devotionalId}") { backStackEntry ->
            val devotionalId =
                backStackEntry.arguments?.getString("devotionalId")?.toIntOrNull() ?: 0

                DetailScreen(
                devotionalId = devotionalId,
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
                devotionals = devotionals,
                favoriteViewModel = favoriteViewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onClickDetail = { devotionalId ->
                    navController.navigate("detail/${devotionalId}")
                }
            )
        }
    }
}