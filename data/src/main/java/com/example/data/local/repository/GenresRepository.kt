package com.example.data.local.repository

import com.example.data.local.model.GenresLocalDB
import kotlinx.coroutines.flow.Flow

interface GenresRepository {
    suspend fun insertAllGenres(list: List<GenresLocalDB>)
    fun getGenres(): Flow<List<GenresLocalDB>>
    suspend fun deleteAll()
    suspend fun getGenresFromApiAndAddToDB(): Flow<List<GenresLocalDB>>
}