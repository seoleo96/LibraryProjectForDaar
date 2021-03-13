package com.example.data.local.dao


import androidx.room.*
import com.example.data.local.model.BookLocalDB
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BookLocalDB>)

    @Query("SELECT * FROM books ORDER BY id ASC")
    fun getBooks(): Flow<List<BookLocalDB>>

    @Query("DELETE FROM books")
    fun deleteAll()

    @Query("SELECT * FROM books WHERE name LIKE :query OR author LIKE :query")
    fun searchDatabase(query: String): Flow<List<BookLocalDB>>

    @Query("SELECT * FROM books WHERE genre_id IN (:id)")
    fun sortByIdGenres(id:List<String>): Flow<List<BookLocalDB>>

}