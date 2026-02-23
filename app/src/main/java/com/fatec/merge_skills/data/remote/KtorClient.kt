package com.fatec.merge_skills.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Cliente HTTP Base configurado com Ktor.
 * 
 * Este arquivo abstrai o motor nativo (Android) e aplica o negociador de conteúdo (JSON)
 * permitindo ignorar chaves desconhecidas da API.
 */
object KtorClient {
    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Se a API enviar campos extras, não dá crash
                isLenient = true // Aceita JSON mal formatado em alguns casos
            })
        }
    }
}
