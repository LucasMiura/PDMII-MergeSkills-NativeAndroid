package com.fatec.merge_skills.ui.screens.aula05

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Aula05ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Aula05UiState())
    val uiState: StateFlow<Aula05UiState> = _uiState.asStateFlow()

    fun onAction(action: Aula05Action) {
        when (action) {
            is Aula05Action.OnNameChanged -> {
                _uiState.update { it.copy(userName = action.name, errorMessage = null) }
            }
            is Aula05Action.OnSubmitClicked -> {
                submitData()
            }
            is Aula05Action.OnDismissMessage -> {
                _uiState.update { it.copy(successMessage = null, errorMessage = null) }
            }
        }
    }

    private fun submitData() {
        val currentName = _uiState.value.userName
        if (currentName.isBlank()) {
            _uiState.update { it.copy(errorMessage = "O nome não pode ser vazio") }
            return
        }

        // Simulando fluxo assíncrono básico sem rede real ainda
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            delay(1500) // Fake network delay
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    successMessage = "Usuário $currentName cadastrado com sucesso!"
                )
            }
        }
    }
}
