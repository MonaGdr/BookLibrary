package com.example.booklibrary.data.remote.api

import com.example.booklibrary.data.remote.model.BookResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("books/")
    suspend fun getBooks(
        @Query("page") page: Int = 1
    ): BookResponseDto
}