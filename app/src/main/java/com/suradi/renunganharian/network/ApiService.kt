package com.suradi.renunganharian.network

import com.suradi.renunganharian.model.Devotional
import retrofit2.http.GET

interface ApiService {

    @GET("api/devotionals")
    suspend fun getAllDevotionals(): List<Devotional>

    @GET("api/devotionals/previous")
    suspend fun getPreviousDevotionals(): List<Devotional>
}