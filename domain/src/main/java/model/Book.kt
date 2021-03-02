package model

data class Book(
    val id : String,
    val isbn : String,
    val title : String,
    val author : String,
    val image : String? = "",
    val publish_date : String,
    val genre_id : String? = "",
    val createdAt : String,
    val updatedAt : String,
)