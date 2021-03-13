package com.example.base

import android.app.Application
import com.example.data.local.repository.BookRepositoryImpl
import com.example.data.local.BookDatabase
import com.example.data.local.repository.GenresRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    val database by lazy { BookDatabase.getDatabase(this) }
    val repository by lazy { BookRepositoryImpl(database.bookDao()) }
    val repositoryGenres by lazy { GenresRepositoryImpl(database.genresDao()) }
}


