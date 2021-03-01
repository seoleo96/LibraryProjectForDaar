package com.example.data.local.repository

import com.example.data.local.BookDao
import com.example.data.local.model.BookLocalDB
import com.example.data.local.model.mapToVM
import com.example.data.remote.repository.BookRepositoryImpl
import kotlinx.coroutines.flow.Flow
import model.Book

class BookRepositoryImpl(private val bookDao: BookDao) :BookRepository {

    override suspend fun insertAll(list: List<BookLocalDB>) {
        bookDao.insertAll(list)
    }

    override fun getBooks(): Flow<List<BookLocalDB>> {
        return bookDao.getBooks()
    }

    override suspend fun deleteAll() = bookDao.deleteAll()

    override suspend fun getDataFromApiAndAddToDB() : List<BookLocalDB> {
        return BookRepositoryImpl().getBooks().map { it.mapToVM() }
    }

}


