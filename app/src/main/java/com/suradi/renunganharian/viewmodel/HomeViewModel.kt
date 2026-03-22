package com.suradi.renunganharian.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suradi.renunganharian.data.repository.DevotionalRepository
import com.suradi.renunganharian.model.Devotional
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel : ViewModel() {

    private val repository = DevotionalRepository()

    private val _devotionals = MutableStateFlow<List<Devotional>>(emptyList())
    val devotionals: StateFlow<List<Devotional>> = _devotionals

    private val _todayDevotional = MutableStateFlow<Devotional?>(null)
    val todayDevotional: StateFlow<Devotional?> = _todayDevotional

    init {
        fetchDevotionals()
    }

    private fun fetchDevotionals() {
        viewModelScope.launch {
            try {
                val result = repository.getAllDevotionals()
                _devotionals.value = result
                setTodayFromList(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setTodayFromList(items: List<Devotional>) {
        val today = LocalDate.now()

        _todayDevotional.value = items.find { devotional ->
            devotional.month == today.monthValue &&
                    devotional.day == today.dayOfMonth
        }
    }
}