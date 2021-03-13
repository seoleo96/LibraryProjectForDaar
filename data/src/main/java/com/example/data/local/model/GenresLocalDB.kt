package com.example.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import model.Book
import model.Genres


@Entity(tableName = "genres")
data class GenresLocalDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val createdAt: String,
    val updatedAt: String,
    val sort: Int,
    val enabled: Boolean,
)

fun Genres.mapGenresToVM(): GenresLocalDB {
    return GenresLocalDB(
        id = this.id.toInt(),
        title = this.title,
        sort = this.sort,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        enabled = this.enabled
    )
}




