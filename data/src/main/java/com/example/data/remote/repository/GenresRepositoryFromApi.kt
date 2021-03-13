package com.example.data.remote.repository

import model.Genres


interface GenresRepositoryFromApi {
    suspend fun getGenres():List<Genres>
}