package com.example.submissioncompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.KomikRepository
import com.example.submissioncompose.model.Komik
import com.example.submissioncompose.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: KomikRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Komik>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Komik>>>
        get() = _uiState

    fun getAllTools() {
        viewModelScope.launch {
            repository.getAllTools()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { tools ->
                    _uiState.value = UiState.Success(tools)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _uiState.value = UiState.Success(
            repository.searchTools(_query.value)
                .sortedBy { it.name }
        )
    }
}