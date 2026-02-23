package com.fatec.merge_skills.domain.models

import kotlinx.serialization.Serializable

/**
 * Modelo de dados que representa um Post simulado recebido da Web.
 * 
 * Anotação @Serializable: Avisa ao Kotlinx.Serialization que esta classe 
 * pode ser convertida automaticamente de/para JSON. 
 */
@Serializable
data class PostInfo(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)
