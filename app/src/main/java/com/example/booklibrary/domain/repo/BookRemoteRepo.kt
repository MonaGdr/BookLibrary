package com.example.booklibrary.domain.repo

import com.example.booklibrary.domain.model.Book

interface BookRemoteRepo {
    suspend fun getBooks(page: Int): List<Book>
}