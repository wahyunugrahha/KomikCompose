package com.example.submissioncompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.KomikRepository
import com.example.submissioncompose.model.Komik
import com.example.submissioncompose.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: KomikRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Komik>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Komik>>
        get() = _uiState

    fun getToolByName(name: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getToolByName(name))
        }
    }
}