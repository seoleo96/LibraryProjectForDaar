package com.example.bottomnavigation.repository


import com.example.bottomnavigation.data.BookDao
import com.example.bottomnavigation.data.model.BookDBO
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    val getBooks: Flow<List<BookDBO>> = bookDao.getBooks()

    suspend fun insertAll(list: List<BookDBO>) {
        bookDao.insertAll(list)
    }

}