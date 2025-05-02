package com.example.booklibrary.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class BookResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BookDto>
)