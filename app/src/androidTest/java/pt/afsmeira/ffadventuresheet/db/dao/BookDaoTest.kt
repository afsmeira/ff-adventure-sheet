package pt.afsmeira.ffadventuresheet.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.util.FFAdventureSheetDatabaseRule
import pt.afsmeira.ffadventuresheet.util.observeAndAwait

@RunWith(AndroidJUnit4::class)
class BookDaoTest {

    var dbRule = FFAdventureSheetDatabaseRule()

    @Rule
    @JvmField
    var ruleChain = RuleChain.outerRule(dbRule).around(InstantTaskExecutorRule())

    @Test
    fun listAllBooksTest() {
        assertThat(dbRule.db.bookDao().listAll().observeAndAwait().size, `is`(69))
    }
}
