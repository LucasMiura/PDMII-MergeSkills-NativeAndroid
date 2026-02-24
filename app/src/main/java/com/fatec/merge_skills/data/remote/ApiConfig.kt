package com.fatec.merge_skills.data.remote

/**
 * Configurações Centralizadas da API do Projeto.
 */
object ApiConfig {
    // URL Real do Backend no Render
    const val BASE_URL = "https://lddm-api-inicial-1.onrender.com"

    // Chave de API do Supabase (Anon Key) extraída do projeto base
    const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZsaWluYXFmbnB5bGJ4eHhpYXdzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzA5OTE5MzIsImV4cCI6MjA4NjU2NzkzMn0.fsh7v8vcbYJYs6QIflE5jMc7qk3tB979ZtsZaXuyn3M"

    // Endpoints (Caminhos)
    object Endpoints {
        const val LOGIN = "$BASE_URL/auth/login"
        const val REGISTER = "$BASE_URL/auth/register"
        const val POSTS = "https://jsonplaceholder.typicode.com/posts"
    }
}
