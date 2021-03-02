package com.example.data.remote.service


import kotlinx.coroutines.flow.Flow
import model.Book
import retrofit2.http.GET


interface BookService {

    @GET("books")
    suspend fun getBooks():List<Book>

}