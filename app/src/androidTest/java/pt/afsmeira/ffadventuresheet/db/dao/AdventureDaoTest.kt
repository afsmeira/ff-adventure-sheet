package pt.afsmeira.ffadventuresheet.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.util.observeAndAwait
import pt.afsmeira.ffadventuresheet.util.FFAdventureSheetDatabaseRule
import java.time.Instant

@RunWith(AndroidJUnit4::class)
class AdventureDaoTest {

    var dbRule = FFAdventureSheetDatabaseRule()

    @Rule
    @JvmField
    var ruleChain: RuleChain = RuleChain.outerRule(dbRule).around(InstantTaskExecutorRule())

    /**
     * Test that the expected number of adventures is returned and that they are in the expected
     * order.
     */
    @Test
    fun listAdventureBooksTest() {
        runBlocking {
            for (i in 1L..2L) {
                val adventure = Adventure(
                    bookId = i,
                    createdAt = Instant.now(),
                    updatedAt = Instant.now().plusSeconds(i)
                )
                dbRule.db.adventureDao().create(adventure)
            }
        }

        val adventures = dbRule.db.adventureDao().listAll().observeAndAwait()
        assertThat(adventures.size, `is`(2))
        assertThat(adventures[0].book.id, `is`(2L))
    }
}
