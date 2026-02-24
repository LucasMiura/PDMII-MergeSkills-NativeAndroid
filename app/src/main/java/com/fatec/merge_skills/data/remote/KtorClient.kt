package com.fatec.merge_skills.data.remote

import android.net.http.HttpResponseCache.install
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Cliente HTTP Base configurado com Ktor.
 * 
 * Agora configurado para enviar a Supabase Key e o Content-Type JSON 
 * em todas as requisições automaticamente.
 */
object KtorClient {
    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true 
                isLenient = true 
            })
        }

        // Configuração padrão para todas as requisições
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
            header("apikey", ApiConfig.SUPABASE_KEY)
            header("Authorization", "Bearer ${ApiConfig.SUPABASE_KEY}")
        }
    }
}
