package com.example.base

import android.app.Application
import com.example.data.local.repository.BookRepositoryImpl
import com.example.data.local.BookDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { BookDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BookRepositoryImpl(database.bookDao()) }
}


