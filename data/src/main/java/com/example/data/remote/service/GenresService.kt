package com.example.data.remote.service

import model.Genres
import retrofit2.http.GET

interface GenresService {

        @GET("genres")
        suspend fun getGenres():List<Genres>

}