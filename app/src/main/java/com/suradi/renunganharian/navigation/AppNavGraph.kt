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
fun AppNavGraph() { // composable yang mengatur seluruh navigasi aplikasi
    val navController = rememberNavController() // membuat controller navigasi dan menyimpannya selama composable masih aktif
    val favoriteViewModel: FavoriteViewModel = viewModel() // membuat satu instance FavoriteViewModel yang bisa dipakai bersama oleh beberapa screen
    val homeViewModel: HomeViewModel = viewModel()

    val devotionals by homeViewModel.devotionals.collectAsState()

    val previousDevotionals by homeViewModel.previousDevotionals.collectAsState()

    NavHost(                           // container utama untuk navigasi
        navController = navController,
        startDestination = "home" // halaman pertama yang dibuka
    ) {
        composable("home") { // mendefinisikan route home
            HomeScreen(
                onClickDetail = { devotionalId ->
                    navController.navigate("detail/$devotionalId") // saat item home ditekan, aplikasi berpindah ke route detail
                },
                onClickPreviousDevotions = {
                    homeViewModel.fetchPreviousDevotionals()
                    navController.navigate("previous_devotions")
                }
            )
        }

        composable("previous_devotions") {
            PreviousDevotionsScreen(
                devotionals = previousDevotionals,
                onBackClick = {
                    navController.popBackStack()
                },
                onItemClick = { devotionalId ->
                    navController.navigate("detail/$devotionalId")
                }
            )
        }

        composable("detail/{devotionalId}") { backStackEntry -> // halaman detail
            val devotionalId =
                backStackEntry.arguments?.getString("devotionalId")?.toIntOrNull() ?: 0

                DetailScreen(
                devotionalId = devotionalId,
                onBackClick = {
                    navController.popBackStack() // kembali ke halaman sebelumnya
                },
                onFavoriteClick = {
                    navController.navigate("favorite")
                },
                favoriteViewModel = favoriteViewModel // mengirim ViewModel yang sama ke DetailScreen agar daftar favorite sinkron
            )
        }

        composable("favorite") { // destinasi baru untuk halaman favorite
            FavoriteScreen( // memanggil halaman favorite
                devotionals = devotionals,
                favoriteViewModel = favoriteViewModel, // mengirim ViewModel yang sama ke FavoriteScreen
                onBackClick = {
                    navController.popBackStack() // mengembalikan pengguna ke halaman sebelumnya
                },
                onClickDetail = { devotionalId ->
                    navController.navigate("detail/${devotionalId}")
                }
            )
        }
    }
}

