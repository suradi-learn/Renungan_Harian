package com.suradi.renunganharian.utils

import androidx.compose.ui.graphics.Color

    fun getTitleColor(month: Int): Color {
        return when (month) {
            1 -> Color(0xFF3A5A80)
            2 -> Color(0xFF7A2632)
            3 -> Color(0xFF5A3326)
            4 -> Color(0xFF6A3E5C)
            5 -> Color(0xFF3F4A30)
            6 -> Color(0xFF7A3227)
            7 -> Color(0xFF2F4C68)
            8 -> Color(0xFFB03030)
            9 -> Color(0xFF3A231C)
            10 -> Color(0xFF5A343E)
            11 -> Color(0xFF2F3B29)
            12 -> Color(0xFF8C3B30)
            else -> Color.Black
        }
    }

    fun getCardColor(month: Int): Color {
        return when (month) {
            1 -> Color(0xFFD6E3F0)
            2 -> Color(0xFFE8C9CE)
            3 -> Color(0xFFE0CFC5)
            4 -> Color(0xFFDCC9D6)
            5 -> Color(0xFFD6DBC8)
            6 -> Color(0xFFE6CFC9)
            7 -> Color(0xFFD3DFEA)
            8 -> Color(0xFFEBC6C6)
            9 -> Color(0xFFE0CFC8)
            10 -> Color(0xFFE3CDD3)
            11 -> Color(0xFFD5DCCD)
            12 -> Color(0xFFE7CFCB)
            else -> Color.White
        }
    }



    fun getMonthName(month: Int): String { // mengubah angka bulan menjadi nama bulan
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
            else -> "Tidak Diketahui"
        }
    }

    fun formatDevotionalDate(day: Int, month: Int): String { // menggabungkan hari dan bulan
        return "$day ${getMonthName(month)}"
    }
