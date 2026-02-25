package com.fatec.merge_skills.ui.screens.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fatec.merge_skills.data.local.TokenDataStore
import com.fatec.merge_skills.data.remote.ApiConfig
import com.fatec.merge_skills.data.remote.KtorClient
import com.fatec.merge_skills.domain.models.AuthResponse
import com.fatec.merge_skills.domain.models.LoginRequest
import com.fatec.merge_skills.domain.models.RegisterRequest
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

/**
 * ViewModel de autenticação.
 * Persiste o token de sessão no DataStore após login/registro bem-sucedido (Spec 6.4).
 */
class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            try {
                val requestBody = LoginRequest(email = email, password = pass)
                val response: AuthResponse = KtorClient.httpClient.post(ApiConfig.Endpoints.LOGIN) {
                    setBody(requestBody)
                }.body()

                // Persiste o token no DataStore (Spec 6.4)
                response.token?.let { token ->
                    TokenDataStore.saveToken(getApplication(), token)
                }

                _uiState.value = AuthUiState(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = AuthUiState(
                    isLoading = false,
                    errorMessage = "Erro ao entrar: Verifique suas credenciais."
                )
            }
        }
    }

    fun register(name: String, email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            try {
                val requestBody = RegisterRequest(name = name, email = email, password = pass)
                val response: AuthResponse = KtorClient.httpClient.post(ApiConfig.Endpoints.REGISTER) {
                    setBody(requestBody)
                }.body()

                // Persiste o token no DataStore (Spec 6.4)
                response.token?.let { token ->
                    TokenDataStore.saveToken(getApplication(), token)
                }

                _uiState.value = AuthUiState(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = AuthUiState(
                    isLoading = false,
                    errorMessage = "Erro ao cadastrar: E-mail já existe ou falha na rede."
                )
            }
        }
    }

    fun resetError() {
        if (_uiState.value.errorMessage != null) {
            _uiState.value = _uiState.value.copy(errorMessage = null)
        }
    }

    fun resetSuccess() {
        _uiState.value = _uiState.value.copy(isSuccess = false)
    }
}
