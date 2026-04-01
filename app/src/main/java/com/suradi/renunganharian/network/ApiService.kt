package com.suradi.renunganharian.network

import com.suradi.renunganharian.model.Devotional
import retrofit2.http.GET

interface ApiService {

    @GET("api/devotionals") // endpoint API
    suspend fun getAllDevotionals(): List<Devotional> // suspend adalah coroutine async dan List<Devotional> adalah response JSON menjadi list object

    @GET("api/devotionals/previous")
    suspend fun getPreviousDevotionals(): List<Devotional>
}