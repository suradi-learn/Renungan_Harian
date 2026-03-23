package com.suradi.renunganharian.utils

fun getMonthName(month: Int): String {
    return when (month) {
        1 -> "Januari"
        2 -> "Februari"
        3 -> "Maret"
        4 -> "April"
        5 -> "Mei"
        6 -> "Juni"
        7 -> "Juli"
        8 -> "Agustus"
        9 -> "September"
        10 -> "Oktober"
        11 -> "November"
        12 -> "Desember"
        else -> ""
    }
}

fun formatDevotionalDate(day: Int, month: Int): String {
    return "$day ${getMonthName(month)}"
}