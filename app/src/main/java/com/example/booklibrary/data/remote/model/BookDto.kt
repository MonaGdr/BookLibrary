package com.example.booklibrary.data.remote.model

import com.example.booklibrary.domain.model.Book
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val id: Int,
    val title: String,
    val authors: List<AuthorDto>,
    val summaries: List<String>,
    val translators: List<TranslatorDto>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean,
    @SerialName("media_type") val mediaType: String,
    val formats: Map<String, String>,
    @SerialName("download_count") val downloadCount: Int
){
    fun toDomain(): Book {
        return Book(
            id = id,
            title = title,
            authorName = authors.firstOrNull()?.name,
            summary = summaries.firstOrNull(),
            coverUrl = formats["image/jpeg"],
            downloadCount = downloadCount
        )
    }
}