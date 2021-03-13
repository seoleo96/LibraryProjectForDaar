package com.example.data.remote.repository

import com.example.data.remote.RetrofitFactory
import model.Genres

class GenresRepositoryImpl : GenresRepositoryFromApi {
    override suspend fun getGenres(): List<Genres> {
        return RetrofitFactory.instance.genresService.getGenres()
    }
}