package com.example.booklibrary.data.repo

import com.example.booklibrary.data.NetworkChecker
import com.example.booklibrary.data.local.dao.BookDao
import com.example.booklibrary.data.remote.api.BookApi
import com.example.booklibrary.domain.model.Book
import com.example.booklibrary.domain.repo.BookRepo

class BookRepoImpl(
    private val api: BookApi,
    private val dao: BookDao,
    private val networkChecker: NetworkChecker

) : BookRepo {

    override suspend fun fetchBooks(page: Int): List<Book> {

        return if (networkChecker.isOnline()) {
            try {
                val response = api.getBooks(page)
                val books = response.results.map { it.toDomain() }

                if (page == 1) {
                    dao.clearAll()
                }

                //save to local db
                dao.insertAll(response.results.map { it.toBookEntity() })

                books
            } catch (e: Exception) {
                //if exception local
                dao.getBooksPage(limit = 32, page).map { it.toDomain() }
            }
        } else {
            // just local
            dao.getBooksPage(limit = 32, page).map { it.toDomain() }
        }
    }
}