package com.example.booklibrary.domain.usecase

import com.example.booklibrary.domain.repo.BookRepo


class GetBooks(private val repo: BookRepo) {
    suspend fun invoke(page: Int) = repo.fetchBooksFromApi(page)
}