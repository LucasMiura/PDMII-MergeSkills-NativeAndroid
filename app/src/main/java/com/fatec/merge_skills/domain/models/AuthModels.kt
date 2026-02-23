package com.fatec.merge_skills.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Modelo de Usuário real do projeto LDDM.
 */
@Serializable
data class User(
    val id: Int,
    val username: String,
    val email: String,
    val name: String? = null,
    val role: String = "user",
    @SerialName("profile_picture")
    val profilePictureUrl: String? = null
)

/**
 * Payload para Login na API Real.
 */
@Serializable
data class LoginRequest(
    val email: String,
    val password: String? = null
)

/**
 * Payload para Registro na API Real.
 */
@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String? = null,
    @SerialName("profile_picture")
    val profilePictureUrl: String? = null
)

/**
 * Resposta padrão de Autenticação da API Real.
 */
@Serializable
data class AuthResponse(
    val token: String? = null,
    val user: User
)
