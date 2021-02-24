package com.example.bottomnavigation.repository


import com.example.bottomnavigation.data.BookDao
import com.example.bottomnavigation.data.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    val getBooks: Flow<List<Book>> = bookDao.getBooks()

    suspend fun insertAll(list: List<Book>) {
        bookDao.insertAll(list)
    }

}