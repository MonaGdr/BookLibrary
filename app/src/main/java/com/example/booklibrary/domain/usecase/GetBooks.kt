package com.example.booklibrary.domain.usecase

import com.example.booklibrary.domain.repo.BookRemoteRepo

class GetBooks(private val repo: BookRemoteRepo) {
    suspend fun invoke(page: Int) = repo.getBooks(page)
}