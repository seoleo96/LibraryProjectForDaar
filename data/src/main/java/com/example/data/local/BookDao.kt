package com.example.data.local


import androidx.room.*
import com.example.data.local.model.BookLocalDB
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<BookLocalDB>)

    @Query("SELECT * FROM books ORDER BY id ASC")
    fun getBooks() : Flow<List<BookLocalDB>>

    @Query("DELETE FROM books")
    fun deleteAll()

}