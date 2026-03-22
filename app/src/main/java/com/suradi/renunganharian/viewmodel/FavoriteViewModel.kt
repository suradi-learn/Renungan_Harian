package com.suradi.renunganharian.viewmodel

import androidx.lifecycle.ViewModel
import com.suradi.renunganharian.model.Devotional
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FavoriteViewModel : ViewModel() {

    private val _favorites = MutableStateFlow<List<Devotional>>(emptyList())
    val favorites: StateFlow<List<Devotional>> = _favorites

    fun toggleFavorite(devotional: Devotional) {
        _favorites.update { currentFavorites ->
            val isAlreadyFavorite = currentFavorites.any { it.id == devotional.id }

            if (isAlreadyFavorite) {
                currentFavorites.filter { it.id != devotional.id }
            } else {
                currentFavorites + devotional
            }
        }
    }

    fun isFavorite(devotional: Devotional): Boolean {
        return _favorites.value.any { it.id == devotional.id }
    }

    fun removeFavorite(devotional: Devotional) {
        _favorites.update { currentFavorites ->
            currentFavorites.filter { it.id != devotional.id }
        }
    }
}