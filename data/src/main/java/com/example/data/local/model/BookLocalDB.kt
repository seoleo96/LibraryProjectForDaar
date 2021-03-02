package com.example.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import model.Book


@Entity(tableName = "books")
data class BookLocalDB(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val author : String,
    val imageUrl : String
)

fun Book.mapToVM() : BookLocalDB{
    return BookLocalDB(id = this.id.toInt(), name = this.title, imageUrl = this.image.toString(), author = this.id)
}




