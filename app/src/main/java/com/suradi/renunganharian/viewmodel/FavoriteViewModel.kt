package com.suradi.renunganharian.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.suradi.renunganharian.model.Devotional

class FavoriteViewModel : ViewModel() {

    private val _favorites = mutableStateListOf<Devotional>()
    val favorites: List<Devotional> = _favorites

    fun toggleFavorite(devotional: Devotional) {
        if (_favorites.contains(devotional)) {
            _favorites.remove(devotional)
            devotional.isFavorite = false
        } else {
            _favorites.add(devotional)
            devotional.isFavorite = true
        }
    }

    fun isFavorite(devotional: Devotional): Boolean {
        return _favorites.any { it.id == devotional.id}
    }
}