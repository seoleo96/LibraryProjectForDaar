package com.example.data.repository

import com.example.data.local.BookDao
import com.example.data.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(private val bookDao: BookDao) {

    suspend fun insertAll(list: List<Book>) {
        bookDao.insertAll(list)
    }

    fun getBooks(): Flow<List<Book>> {
        return bookDao.getBooks()
    }


    fun deleteAll() = bookDao.deleteAll()


}


