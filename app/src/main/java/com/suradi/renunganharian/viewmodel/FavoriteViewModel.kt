package com.suradi.renunganharian.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.suradi.renunganharian.data.local.FavoriteDataStore
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = FavoriteDataStore(application)

    private val _favoriteIds = mutableStateOf<Set<Int>>(emptySet())
    val favoriteIds: State<Set<Int>> = _favoriteIds

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            dataStore.favoriteIdsFlow.collect { ids ->
                _favoriteIds.value = ids
            }
        }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            val updatedIds = if (_favoriteIds.value.contains(id)) {
                _favoriteIds.value - id
            } else {
                _favoriteIds.value + id
            }

            _favoriteIds.value = updatedIds
            dataStore.saveFavoriteIds(updatedIds)
        }
    }

    fun isFavorite(id: Int): Boolean {
        return _favoriteIds.value.contains(id)
    }
}