package com.example.base.repository


import android.util.Log
import com.example.data.BookDao
import com.example.data.model.Book
import data.network.RetrofitFactory
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(private val bookDao: BookDao) : BookRepository {

    override suspend fun insertAll(list: List<Book>) {
        bookDao.insertAll(list)
    }

    override fun getBooks(): Flow<List<Book>> {
        return bookDao.getBooks()
    }

    override suspend fun getBooksFromApi() : List<Book> {
        return RetrofitFactory.instance.booksService.getBooks().map {
           Book(id = it.id.toInt(), name = it.title, imageUrl = it.author )
        }
    }

    override suspend fun deleteAll() = bookDao.deleteAll()



}


