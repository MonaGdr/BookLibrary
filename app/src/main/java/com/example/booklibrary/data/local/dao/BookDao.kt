package com.example.booklibrary.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booklibrary.data.local.model.BookEntity

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM books ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getBooksPage(limit: Int, offset: Int): List<BookEntity>

    @Query("DELETE FROM books")
    suspend fun clearAll()

}
