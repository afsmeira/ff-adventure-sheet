package pt.afsmeira.ffadventuresheet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.afsmeira.ffadventuresheet.db.dao.AdventureDao
import pt.afsmeira.ffadventuresheet.db.dao.BookDao
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.Book

@Database(entities = arrayOf(Book::class, Adventure::class), version = 1)
abstract class FFAdventureSheetDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun adventureDao(): AdventureDao

    companion object {
        private const val DB_NAME = "ff-adventure-sheet"
        private const val DB_FILE_PATH = "database/$DB_NAME-v01.db"

        @Volatile private var INSTANCE: FFAdventureSheetDatabase? = null

        fun get(context: Context): FFAdventureSheetDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: initDatabase(context).also { INSTANCE = it }
            }

        private fun initDatabase(context: Context) =
            Room
                .databaseBuilder(
                    context.applicationContext,
                    FFAdventureSheetDatabase::class.java,
                    DB_NAME
                )
                .createFromAsset(DB_FILE_PATH)
                .build()
    }
}
