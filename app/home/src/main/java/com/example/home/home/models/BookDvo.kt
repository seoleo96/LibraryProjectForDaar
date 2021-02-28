package com.example.home.home.models

import data.model.BookRemote


data class BookDvo(
    val id: Int = 0,
    val name: String,
    val imageUrl: String
)

fun BookRemote.mapToBook():BookDvo{
    return BookDvo(id = this.id.toInt(), name = this.title, imageUrl = this.author)
}


