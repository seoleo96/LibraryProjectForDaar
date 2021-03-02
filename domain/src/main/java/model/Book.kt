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

/*
* "id": 1,
    "isbn": "1231545845454",
    "title": "Test 2",
    "author": "Great Author",
    "image": null,
    "publish_date": "2018-02-22T12:43:35.210Z",
    "genre_id": null,
    "createdAt": "2021-02-23T06:11:08.041Z",
    "updatedAt": "2021-02-23T06:15:02.058Z"*/
