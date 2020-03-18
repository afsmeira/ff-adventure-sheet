package pt.afsmeira.ffadventuresheet.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.util.WithDB
import pt.afsmeira.ffadventuresheet.util.observeAndAwait

@RunWith(AndroidJUnit4::class)
class BookDaoTest : WithDB() {

    @Test
    fun listAllBooksTest() {
        assertThat(db.bookDao().listAll().observeAndAwait().size, `is`(69))
    }
}
