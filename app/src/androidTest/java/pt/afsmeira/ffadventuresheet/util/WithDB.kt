package pt.afsmeira.ffadventuresheet.util

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.db.InitialState

/**
 * Helper class for tests that need to access a [FFAdventureSheetDatabase]. It provides the
 * following guarantees:
 *
 * - creates a DB before each test, with [InitialState].
 * - closes the created DB after each test.
 * - provides a [InstantTaskExecutorRule] for observing [LiveData] queries.
 */
abstract class WithDB {

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var db: FFAdventureSheetDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FFAdventureSheetDatabase::class.java).build()

        runBlocking(Dispatchers.IO) {
            db.bookDao().create(InitialState.books)
            db.statDao().create(InitialState.stats)
            db.bookStatDao().create(InitialState.bookStats)
        }
    }

    @After
    fun closeDB() {
        db.close()
    }
}
