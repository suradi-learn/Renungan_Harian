package com.suradi.renunganharian.data.repository

import com.suradi.renunganharian.model.Devotional
import com.suradi.renunganharian.network.RetrofitInstance

class DevotionalRepository {

    suspend fun getAllDevotionals(): List<Devotional> {
        return RetrofitInstance.api.getAllDevotionals()
    }

    suspend fun getDevotionalById(id: Int): Devotional? {
        return RetrofitInstance.api.getAllDevotionals().find { it.id == id }
    }
}