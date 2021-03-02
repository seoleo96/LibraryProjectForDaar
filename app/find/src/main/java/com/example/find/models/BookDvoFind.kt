package com.example.find.models

import com.example.data.local.model.BookLocalDB


data class BookDvoFind(
    val id: Int = 0,
    val name: String,
    val author: String,
    val imageUrl: String
)

fun BookLocalDB.mapToUI(): BookDvoFind {
    return BookDvoFind(
        id = this.id, name = this.name, imageUrl = this.imageUrl, author = this.author
    )
}
