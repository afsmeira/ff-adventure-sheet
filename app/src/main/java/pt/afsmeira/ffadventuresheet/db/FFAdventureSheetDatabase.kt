package pt.afsmeira.ffadventuresheet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pt.afsmeira.ffadventuresheet.db.converters.InstantConverter
import pt.afsmeira.ffadventuresheet.db.converters.StatTypeConverter
import pt.afsmeira.ffadventuresheet.db.converters.StringArrayConverter
import pt.afsmeira.ffadventuresheet.db.dao.AdventureDao
import pt.afsmeira.ffadventuresheet.db.dao.BookDao
import pt.afsmeira.ffadventuresheet.db.dao.StatDao
import pt.afsmeira.ffadventuresheet.model.*

/**
 * The FF Adventure Sheet database.
 */
@Database(
    entities = [
        Book::class,
        Adventure::class,
        Stat::class,
        BookStat::class,
        AdventureStat::class
    ],
    version = 1
)
@TypeConverters(InstantConverter::class, StringArrayConverter::class, StatTypeConverter::class)
abstract class FFAdventureSheetDatabase : RoomDatabase() {

    // Data Access Objects
    abstract fun bookDao(): BookDao
    abstract fun adventureDao(): AdventureDao
    abstract fun statDao(): StatDao

    companion object {
        private const val DB_NAME = "ff-adventure-sheet"

        /**
         * The path for the database file, within the assets folder.
         */
        private const val DB_FILE_PATH = "database/$DB_NAME-v01.db"

        /**
         * The singleton database object.
         */
        @Volatile private var INSTANCE: FFAdventureSheetDatabase? = null

        /**
         * Get the database singleton, if it's already initialized. Otherwise, initialize it and
         * then return it.
         *
         * @param context The application context.
         */
        fun get(context: Context): FFAdventureSheetDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: initDatabase(context).also { INSTANCE = it }
            }

        /**
         * Initialize the database, by either loading it from a file if it doesn't exist already,
         * or loading the existing database.
         */
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
