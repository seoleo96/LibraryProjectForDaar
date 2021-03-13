package com.example.data.local.repository

import com.example.data.local.dao.GenresDao
import com.example.data.local.model.GenresLocalDB
import com.example.data.local.model.mapGenresToVM
import com.example.data.remote.repository.GenresRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GenresRepositoryImpl(private val genresDao: GenresDao) : GenresRepository {
    override suspend fun insertAllGenres(list: List<GenresLocalDB>) {
        genresDao.insertAllGenres(list)
    }

    override fun getGenres(): Flow<List<GenresLocalDB>> {
        return genresDao.getGenresFromDB()
    }

    override suspend fun deleteAll() {
        genresDao.deleteAllGenres()
    }

    override suspend fun getGenresFromApiAndAddToDB(): Flow<List<GenresLocalDB>> {
        return flow {
            val hotList = GenresRepositoryImpl().getGenres().map { it.mapGenresToVM() }
            emit(hotList)
        }.flowOn(Dispatchers.IO)
    }
}