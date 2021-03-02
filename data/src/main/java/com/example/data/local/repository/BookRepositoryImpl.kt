package com.example.data.local.repository

import com.example.data.local.BookDao
import com.example.data.local.model.BookLocalDB
import com.example.data.local.model.mapToVM
import com.example.data.remote.repository.BookRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BookRepositoryImpl(private val bookDao: BookDao) : BookRepository {

    override suspend fun insertAll(list: List<BookLocalDB>) {
        bookDao.insertAll(list)
    }

    override fun getBooks(): Flow<List<BookLocalDB>> {

        return bookDao.getBooks()
    }

    override suspend fun deleteAll() = bookDao.deleteAll()

    override suspend fun getDataFromApiAndAddToDB(): Flow<List<BookLocalDB>> {
        return flow {
            val hotList = BookRepositoryImpl().getBooks().map { it.mapToVM() }
            emit(hotList)
        }.flowOn(Dispatchers.IO)
    }

    override fun searchDatabase(query: String): Flow<List<BookLocalDB>> {
        return bookDao.searchDatabase(query)
    }

    override fun sortByIdASC(): Flow<List<BookLocalDB>> {
        return bookDao.sortByIdASC()
    }

    override fun sortByIdDESC(): Flow<List<BookLocalDB>> {
        return bookDao.sortByIdDESC()

    }

}


