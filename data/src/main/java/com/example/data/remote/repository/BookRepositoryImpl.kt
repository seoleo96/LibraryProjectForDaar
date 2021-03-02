package com.example.data.remote.repository

import com.example.data.remote.RetrofitFactory
import model.Book

class BookRepositoryImpl : BookRepositoryFromApi {
    override suspend fun getBooks(): List<Book> {
        return RetrofitFactory.instance.bookService.getBooks()
    }
}