package com.example.data


import androidx.room.*
import com.example.data.model.Book
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<Book>)

    @Query("SELECT * FROM books ORDER BY id ASC")
    fun getBooks() : Flow<List<Book>>

    @Query("DELETE FROM books")
    fun deleteAll()

}