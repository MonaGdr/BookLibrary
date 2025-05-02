package com.example.booklibrary.data.repo

import com.example.booklibrary.data.local.dao.BookDao
import com.example.booklibrary.data.local.model.BookEntity
import com.example.booklibrary.data.remote.api.BookApi
import com.example.booklibrary.domain.model.Book
import com.example.booklibrary.domain.repo.BookRepo

class BookRepoImpl(
    private val api: BookApi,
    private val dao: BookDao
) : BookRepo {
    override suspend fun getBooksLocally(): List<BookEntity> {
        return dao.getAllBooks()
    }

    override suspend fun fetchBooksFromApi(page: Int): List<Book> {
        try {
        //api
        val books = api.getBooks(page)

        //map BookResponseDto to BookEntity
        val bookEntities = books.results.map { it.toBookEntity() }
        // save api result into local database
        dao.insert(bookEntities)

        return books.results.map { it.toDomain() }
    } catch (e: Exception) {
        return dao.getAllBooks().map { it.toDomain() }
    }
}
}