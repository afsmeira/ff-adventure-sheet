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
class BookDaoTest {

    @Rule
    @JvmField
    var dbRule = FFAdventureSheetDatabaseRule()

    @Test
    fun listAllBooksTest() {
        runBlocking {
            assertThat(dbRule.db.bookDao().listAll().size, `is`(69))
        }
    }
}
