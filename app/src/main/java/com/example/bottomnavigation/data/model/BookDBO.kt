package com.example.bottomnavigation.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books")
data class BookDBO(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val imageUrl : String
)
