package com.example.data.local.repository

import com.example.data.local.model.BookLocalDB
import kotlinx.coroutines.flow.Flow
import model.Book

interface BookRepository {
    suspend fun insertAll(list: List<BookLocalDB>)
    fun getBooks(): Flow<List<BookLocalDB>>
    suspend fun deleteAll()
    suspend fun getDataFromApiAndAddToDB():List<BookLocalDB>
}