package com.example.data.local.repository

import com.example.data.local.model.BookLocalDB
import kotlinx.coroutines.flow.Flow
import model.Book

interface BookRepository {
    suspend fun insertAll(list: List<BookLocalDB>)
    fun getBooks(): Flow<List<BookLocalDB>>
    suspend fun deleteAll()
    suspend fun getDataFromApiAndAddToDB():Flow<List<BookLocalDB>>
    fun searchDatabase(query : String): Flow<List<BookLocalDB>>
    fun sortByIdASC() : Flow<List<BookLocalDB>>
    fun sortByIdDESC() : Flow<List<BookLocalDB>>
}