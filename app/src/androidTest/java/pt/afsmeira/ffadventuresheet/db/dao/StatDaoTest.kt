package pt.afsmeira.ffadventuresheet.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.util.FFAdventureSheetDatabaseRule

@RunWith(AndroidJUnit4::class)
class StatDaoTest {

    @Rule
    @JvmField
    var dbRule = FFAdventureSheetDatabaseRule()

    @Test
    fun listStatsForBookTest() {
        runBlocking {
            val stats = dbRule.db.statDao().listForBook(1L)

            assertThat(stats.size, `is`(9))
        }
    }
}
