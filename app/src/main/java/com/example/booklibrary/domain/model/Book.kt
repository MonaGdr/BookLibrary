package com.example.booklibrary.domain.model

data class Book(
    val id: Int,
    val title: String,
    val authorName: String?,
    val summary: String?,
    val coverUrl: String?,
    val downloadCount: Int
)