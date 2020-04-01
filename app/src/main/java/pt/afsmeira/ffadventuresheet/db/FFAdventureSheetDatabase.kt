package pt.afsmeira.ffadventuresheet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pt.afsmeira.ffadventuresheet.db.converters.*
import pt.afsmeira.ffadventuresheet.db.dao.AdventureDao
import pt.afsmeira.ffadventuresheet.db.dao.BookDao
import pt.afsmeira.ffadventuresheet.db.dao.BookStatDao
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
@TypeConverters(
    InstantConverter::class,
    StatPossibleValuesConverter::class,
    StatTypeConverter::class,
    StatValueConverter::class
)
abstract class FFAdventureSheetDatabase : RoomDatabase() {

    // Data Access Objects
    abstract fun bookDao(): BookDao
    abstract fun adventureDao(): AdventureDao
    abstract fun statDao(): StatDao
    abstract fun bookStatDao(): BookStatDao

    companion object {
        private const val DB_NAME = "ff-adventure-sheet"

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
                .addCallback(InitialStateCreation(context))
                .build()
    }

    /**
     * Create the application's initial state after the database is created.
     */
    private class InitialStateCreation(private val context: Context) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            GlobalScope.launch(Dispatchers.IO) {
                get(context).bookDao().create(InitialState.books)
                get(context).statDao().create(InitialState.stats)
                get(context).bookStatDao().create(InitialState.bookStats)
            }
        }
    }
}
