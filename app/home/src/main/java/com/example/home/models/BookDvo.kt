package com.example.home.models

import com.example.data.local.model.BookLocalDB


data class BookDvo(
    val id: Int = 0,
    val name: String,
    val author: String,
    val imageUrl: String
)

fun BookLocalDB.mapToUI(): BookDvo {
    return BookDvo(
        id = this.id, name = this.name, imageUrl = this.imageUrl, author = this.author
    )
}
