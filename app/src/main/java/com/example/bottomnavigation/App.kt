package com.example.bottomnavigation

import android.app.Application
import com.example.bottomnavigation.data.BookDatabase
import com.example.bottomnavigation.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { BookDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BookRepository(database.bookDao()) }
}
//App
//App2 comment 2

