package pt.afsmeira.ffadventuresheet.utils

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.Rule
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase

/**
 * Helper class for tests that need to access a [FFAdventureSheetDatabase]. It provides the
 * following guarantees:
 *
 * - creates a DB before each test.
 * - closes the created DB after each test.
 * - provides a [InstantTaskExecutorRule] for observing [LiveData] queries.
 * - provides an extension point for populating the DB after creation.
 */
abstract class WithDB {

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var db: FFAdventureSheetDatabase

    abstract fun initDB()

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FFAdventureSheetDatabase::class.java).build()

        initDB()
    }

    @After
    fun closeDB() {
        db.close()
    }
}