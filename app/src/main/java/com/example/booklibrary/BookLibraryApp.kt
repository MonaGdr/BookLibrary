package com.example.booklibrary

import android.app.Application
import com.example.booklibrary.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BookLibraryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@BookLibraryApp)
            modules(appModule)
        }
    }
}
