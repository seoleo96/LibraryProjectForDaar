package com.example.data.repository


import com.example.data.BookDao
import com.example.data.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    val getBooks: Flow<List<Book>> = bookDao.getBooks()

    suspend fun insertAll(list: List<Book>) {
        bookDao.insertAll(list)
    }

}