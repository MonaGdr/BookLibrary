package com.example.booklibrary.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.booklibrary.domain.model.Book

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "authorName") val authorName: String?,
    @ColumnInfo(name = "summary") val summary: String?,
    @ColumnInfo(name = "cover_url") val coverUrl: String?,
    @ColumnInfo(name = "download_count") val downloadCount: Int

){
    fun toDomain(): Book {
        return Book(
            id = id,
            title = title,
            authorName = authorName,
            summary = summary,
            coverUrl = coverUrl,
            downloadCount = downloadCount
        )
    }
}
