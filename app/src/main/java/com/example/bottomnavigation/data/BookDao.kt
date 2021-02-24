package com.example.bottomnavigation.data


import androidx.room.*
import com.example.bottomnavigation.data.model.BookDBO
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<BookDBO>)

    @Query("SELECT * FROM books ORDER BY id ASC")
    fun getBooks() : Flow<List<BookDBO>>

    @Query("DELETE FROM books")
    fun deleteAll()

}