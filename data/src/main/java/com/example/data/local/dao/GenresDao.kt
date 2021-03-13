package com.example.data.local.dao


import androidx.room.*
import com.example.data.local.model.BookLocalDB
import com.example.data.local.model.GenresLocalDB
import kotlinx.coroutines.flow.Flow


@Dao
interface GenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGenres(list: List<GenresLocalDB>)

    @Query("SELECT * FROM genres ORDER BY id ASC")
    fun getGenresFromDB() : Flow<List<GenresLocalDB>>

    @Query("DELETE FROM genres")
    fun deleteAllGenres()

}