package com.example.booklibrary.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val name: String,
    @SerialName("birth_year") val birthYear: Int?,
    @SerialName("death_year") val deathYear: Int?
)
