package pt.afsmeira.ffadventuresheet.util

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
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
 *
 * Note that this class will make a real, and possibly existing, DB available for testing. The DB
 * that is made available depends on where the test is running. This means that it's necessary to
 * take care when writing tests to avoid failures. This situation _should not_ be a problem for CI
 * pipelines since each pipeline is run on a clean device.
 */
abstract class WithDB {

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var db: FFAdventureSheetDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = FFAdventureSheetDatabase.get(context)
    }

    @After
    fun closeDB() {
        db.close()
    }
}
