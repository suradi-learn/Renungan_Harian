package com.suradi.renunganharian.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suradi.renunganharian.data.repository.DevotionalRepository
import com.suradi.renunganharian.model.Devotional
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository = DevotionalRepository()

    private val _devotional = MutableStateFlow<Devotional?>(null)
    val devotional: StateFlow<Devotional?> = _devotional

    fun fetchDevotional(id: Int) {
        viewModelScope.launch {
            try {
                _devotional.value = repository.getDevotionalById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}