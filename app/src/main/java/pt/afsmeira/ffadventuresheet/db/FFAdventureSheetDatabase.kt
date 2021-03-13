package pt.afsmeira.ffadventuresheet.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pt.afsmeira.ffadventuresheet.db.converters.*
import pt.afsmeira.ffadventuresheet.db.dao.*
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
        AdventureStat::class,
        Note::class,
        Equipment::class
    ],
    version = 1
)
@TypeConverters(InstantConverter::class, StatTypeConverter::class)
abstract class FFAdventureSheetDatabase : RoomDatabase() {

    // Data Access Objects
    abstract fun bookDao(): BookDao
    abstract fun adventureDao(): AdventureDao
    abstract fun statDao(): StatDao
    abstract fun noteDao(): NoteDao
    abstract fun equipmentDao(): EquipmentDao

    companion object {
        private const val DB_NAME = "ff-adventure-sheet"

        /**
         * The singleton database object.
         */
        @Volatile private var INSTANCE: FFAdventureSheetDatabase? = null

        /**
         * Manually set the DB instance **for testing purposes**.
         */
        @VisibleForTesting
        fun set(ffAdventureSheetDatabase: FFAdventureSheetDatabase) {
            INSTANCE = ffAdventureSheetDatabase
        }

        /**
         * Get the database singleton, if it's already created. Otherwise, create it and then return
         * it.
         *
         * @param context The application context.
         */
        fun get(context: Context): FFAdventureSheetDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            }

        /**
         * Create the database and "schedule" its initialization to after it's created.
         */
        private fun createDatabase(context: Context) =
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
     * Create the application's initial state after the database is created for the first time.
     *
     * **CAUTION** - Initial state creation is done asynchronously on some IO thread, after the DB
     * is already created, so it is possible that DB access in the meanwhile might return the wrong
     * results. In practice, this should not be a problem since the queries performed while the DB
     * in being initialized would return no results anyway. See [AdventuresActivity].
     */
    private class InitialStateCreation(private val context: Context) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            GlobalScope.launch(Dispatchers.IO) {
                get(context).bookDao().create(InitialState.books)
            }
        }
    }
}
