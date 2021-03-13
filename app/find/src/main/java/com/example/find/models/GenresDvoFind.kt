package com.example.find.models


import com.example.data.local.model.GenresLocalDB

data class GenresDvoFind(
    val id: Int = 0,
    val title: String
)

fun GenresLocalDB.mapToUI(): GenresDvoFind {
    return GenresDvoFind(
        id = this.id, title = this.title
    )
}