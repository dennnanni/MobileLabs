package com.example.architetturacompose.ui.screens

import androidx.lifecycle.ViewModel
import com.example.architetturacompose.data.models.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ThemeState(val theme: Theme)

class ThemeToggleViewModel : ViewModel() {
    private val _state = MutableStateFlow(ThemeState(Theme.System))
    val state = _state.asStateFlow()

    fun changeTheme(theme: Theme) {
        _state.value = ThemeState(theme)
    }
}