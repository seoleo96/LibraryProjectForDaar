package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val imageUrl : String
)




