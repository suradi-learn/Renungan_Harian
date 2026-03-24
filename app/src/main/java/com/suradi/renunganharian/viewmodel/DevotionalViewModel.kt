package com.suradi.renunganharian.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suradi.renunganharian.data.repository.DevotionalRepository
import com.suradi.renunganharian.model.Devotional
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DevotionalViewModel : ViewModel() {

    private val repository = DevotionalRepository()

    private val _previousDevotionals = MutableStateFlow<List<Devotional>>(emptyList())
    val previousDevotionals: StateFlow<List<Devotional>> = _previousDevotionals

    private val _devotionals = MutableStateFlow<List<Devotional>>(emptyList())
    val devotionals: StateFlow<List<Devotional>> = _devotionals

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchPreviousDevotionals() {
        viewModelScope.launch {
            try {
                _previousDevotionals.value = repository.getPreviousDevotionals()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchDevotionals() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                _devotionals.value = repository.getAllDevotionals()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}