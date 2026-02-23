package com.fatec.merge_skills.data.remote

/**
 * Configurações Centralizadas da API do Projeto.
 * 
 * Centralizar a URL base evita que tenhamos Strings espalhadas em múltiplos ViewModels,
 * facilitando a troca entre ambiente de Produção (Render) e Local (10.0.2.2).
 */
object ApiConfig {
    // URL Real do Backend no Render
    const val BASE_URL = "https://lddm-api-inicial-1.onrender.com"

    // Endpoints (Caminhos)
    object Endpoints {
        const val LOGIN = "$BASE_URL/auth/login"
        const val REGISTER = "$BASE_URL/auth/register"
        const val POSTS = "https://jsonplaceholder.typicode.com/posts" // API de testes da Aula 07
    }
}
