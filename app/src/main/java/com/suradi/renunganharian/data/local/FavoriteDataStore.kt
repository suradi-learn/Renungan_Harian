package com.suradi.renunganharian.data.local

import android.content.Context
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "favorite_prefs")

class FavoriteDataStore (private val context: Context) {

    companion object {
        private val FAVORITE_IDS_KEY = stringSetPreferencesKey("favorite_ids")
    }

    val favoriteIdsFlow: Flow<Set<Int>> = context.dataStore.data.map { preferences ->
        preferences[FAVORITE_IDS_KEY]
            ?.mapNotNull { it.toIntOrNull() }
            ?.toSet()
            ?: emptySet()
    }

    suspend fun saveFavoriteIds(ids: Set<Int>) {
        context.dataStore.edit { preferences ->
            preferences[FAVORITE_IDS_KEY] = ids.map { it.toString() }.toSet()
        }
    }
}