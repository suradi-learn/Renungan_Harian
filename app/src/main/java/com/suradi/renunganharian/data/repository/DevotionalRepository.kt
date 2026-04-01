package com.suradi.renunganharian.data.repository

import com.suradi.renunganharian.model.Devotional
import com.suradi.renunganharian.network.RetrofitInstance

class DevotionalRepository { // untuk mengambil data API

    suspend fun getAllDevotionals(): List<Devotional> {
        return RetrofitInstance.api.getAllDevotionals()
    }

    suspend fun getPreviousDevotionals(): List<Devotional> {
        return RetrofitInstance.api.getPreviousDevotionals()
    }

    suspend fun getDevotionalById(id: Int): Devotional? {
        return RetrofitInstance.api.getAllDevotionals().find { it.id == id }
    }
}