package com.example.booklibrary.data.di

import androidx.room.Room
import com.example.booklibrary.data.local.db.BookDatabase
import com.example.booklibrary.data.remote.api.BookApi
import com.example.booklibrary.domain.repo.BookRepo
import com.example.booklibrary.data.repo.BookRepoImpl
import com.example.booklibrary.domain.usecase.GetBooks
import com.example.booklibrary.domain.utility.Links
import com.example.booklibrary.presentation.viewModel.BookListViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    val json = Json {
        ignoreUnknownKeys = true
    }
    single { Room.databaseBuilder(get(), BookDatabase::class.java, "book_database").build().bookDao() }

    single {
        Retrofit.Builder()
            .baseUrl(Links.API_ROOT_URI)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(BookApi::class.java)
    }

    single<BookRepo> {
        BookRepoImpl(
            api = get(),
            dao = get()
        )
    }
    single { GetBooks(get()) }
    viewModel { BookListViewModel(get()) }


}
