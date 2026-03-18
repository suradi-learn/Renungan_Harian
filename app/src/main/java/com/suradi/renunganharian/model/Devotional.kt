package com.suradi.renunganharian.model

data class Devotional(
    val id: Int,
    val title: String,
    val verseText: String,
    val verseReference: String,
    val content: String,
    val closing: String,
    val devotionalDate: String
)