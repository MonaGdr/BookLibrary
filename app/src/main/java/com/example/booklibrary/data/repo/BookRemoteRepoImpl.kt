package com.example.booklibrary.data.repo

import com.example.booklibrary.data.remote.api.BookApi
import com.example.booklibrary.domain.model.Book
import com.example.booklibrary.domain.repo.BookRemoteRepo

class BookRemoteRepoImpl(
    private val api: BookApi
) : BookRemoteRepo {
    override suspend fun getBooks(page: Int): List<Book> {
        return api.getBooks(page).results.map { it.toDomain() }
    }
}