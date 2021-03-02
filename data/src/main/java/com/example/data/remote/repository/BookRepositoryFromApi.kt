package com.example.data.remote.repository


import model.Book


interface BookRepositoryFromApi {
    suspend fun getBooks(): List<Book>
}