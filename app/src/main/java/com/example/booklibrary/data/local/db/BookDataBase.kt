package com.example.booklibrary.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booklibrary.data.local.dao.BookDao
import com.example.booklibrary.data.local.model.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
