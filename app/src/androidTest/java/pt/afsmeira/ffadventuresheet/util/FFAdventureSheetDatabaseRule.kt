package pt.afsmeira.ffadventuresheet.util

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.db.InitialState
import java.util.concurrent.Executors

/**
 * Helper [TestRule] for tests that need to access a [FFAdventureSheetDatabase]. It provides the
 * following guarantees:
 *
 * - creates a DB before each test, with [InitialState].
 * - sets the DB as the only instance of [FFAdventureSheetDatabase].
 * - closes the created DB after each test.
 *
 * **This rule should always be the outermost rule of a [RuleChain].**
 */
class FFAdventureSheetDatabaseRule : TestRule {

    lateinit var db: FFAdventureSheetDatabase

    override fun apply(base: Statement?, description: Description?) =

        object : Statement() {

            override fun evaluate() {
                val context = ApplicationProvider.getApplicationContext<Context>()
                db = Room
                    .inMemoryDatabaseBuilder(context, FFAdventureSheetDatabase::class.java)
                    .setTransactionExecutor(Executors.newSingleThreadExecutor())
                    .build()

                runBlocking(Dispatchers.IO) {
                    db.bookDao().create(InitialState.books)

                    FFAdventureSheetDatabase.set(db)
                }

                try {
                    base?.evaluate()
                } finally {
                    db.close()
                }
            }
        }
}
