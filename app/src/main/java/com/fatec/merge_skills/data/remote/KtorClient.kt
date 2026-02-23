package com.fatec.merge_skills.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Cliente HTTP Base configurado com Ktor.
 * 
 * Este Singleton fornece a instância pré-configurada do HttpClient
 * para ser injetada ou utilizada diretamente pelos ViewModels.
 */
object KtorClient {
    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true 
                isLenient = true 
            })
        }
    }
}
