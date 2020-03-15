package pt.afsmeira.ffadventuresheet.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.utils.WithDB
import pt.afsmeira.ffadventuresheet.utils.observeAndAwait

@RunWith(AndroidJUnit4::class)
class BookDaoTest : WithDB() {

    override fun initDB() {
        runBlocking {
            for (i in 1L..10L) {
                val book = Book(i, "Book $i", "Cover $i")
                db.bookDao().create(book)
            }
        }
    }

    @Test
    fun listAllBooksTest() {
        assertThat(db.bookDao().listAll().observeAndAwait().size, `is`(10))
    }
}
