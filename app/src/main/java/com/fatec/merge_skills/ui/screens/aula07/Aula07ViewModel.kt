package com.fatec.merge_skills.ui.screens.aula07

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatec.merge_skills.data.remote.KtorClient
import com.fatec.merge_skills.domain.models.PostInfo
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Estado UI contendo o resultado da tentativa de conexão com a API.
 */
data class Aula07UiState(
    val posts: List<PostInfo> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class Aula07ViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(Aula07UiState())
    val uiState: StateFlow<Aula07UiState> = _uiState.asStateFlow()

    fun fetchPosts() {
        // ViewModelScope é uma Coroutine atrelada à vida útil da Tela.
        // Se a tela for destruída (ex: rotação de tela brusca), a call morre junto,
        // economizando memória e bateria.
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                // Suspende a execução (fora da Main Thread de UI) aguardando o Backend
                val response: List<PostInfo> = KtorClient.httpClient
                    .get("https://jsonplaceholder.typicode.com/posts")
                    .body()
                
                // Sucesso: popula a lista de posts e retira o Loading
                _uiState.value = _uiState.value.copy(
                    posts = response,
                    isLoading = false
                )
            } catch (e: Exception) {
                e.printStackTrace()
                // Erro: exibe a mensagem amigável e retira o Loading
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Falha ao buscar dados: ${e.localizedMessage}"
                )
            }
        }
    }
}
