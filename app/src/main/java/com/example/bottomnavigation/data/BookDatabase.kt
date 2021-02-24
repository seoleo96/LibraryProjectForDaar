package com.example.bottomnavigation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bottomnavigation.data.model.BookDBO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [BookDBO::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    private class BookDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    insertToDatabase(database.bookDao())
                }
            }
        }

        suspend fun insertToDatabase(bookDao: BookDao) {

            bookDao.deleteAll()

            val bookList = listOf<BookDBO>(
                BookDBO(
                    name = "Harry Potter",
                    imageUrl = "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg"
                ),
                BookDBO(
                    name = "Hobbit",
                    imageUrl = "https://d1466nnw0ex81e.cloudfront.net/n_iv/600/611695.jpg"
                ),
                BookDBO(
                    name = "WarCraft",
                    imageUrl = "https://static.wikia.nocookie.net/wowwiki/images/9/99/War_of_the_Ancients_Archive.jpg/revision/latest?cb=20071011115123"
                ),
                BookDBO(
                    name = "Lord of the Rings",
                    imageUrl = "https://d3525k1ryd2155.cloudfront.net/h/259/239/849239259.0.x.jpg"
                ),
                BookDBO(
                    name = "Game oh Thrones",
                    imageUrl = "http://www.randomhousebooks.com/wp-content/uploads/2015/02/9780553593716.jpg"
                )
            )
            scope.launch(Dispatchers.IO) {
                bookDao.insertAll(bookList)
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BookDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "books"
                ).fallbackToDestructiveMigration().addCallback(BookDatabaseCallback(scope)).build()

                INSTANCE = instance
                instance
            }
        }
    }


}
