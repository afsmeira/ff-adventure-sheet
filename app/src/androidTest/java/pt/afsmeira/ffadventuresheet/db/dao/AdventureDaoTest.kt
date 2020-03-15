package pt.afsmeira.ffadventuresheet.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.util.WithDB
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.util.observeAndAwait
import java.time.Instant

@RunWith(AndroidJUnit4::class)
class AdventureDaoTest : WithDB() {

    override fun initDB() {
        runBlocking {
            for (i in 1L..2L) {
                val book = Book(i, "Book $i", "Cover $i")
                db.bookDao().create(book)
            }
        }
    }

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
                db.adventureDao().create(adventure)
            }
        }

        val adventureBooks = db.adventureDao().listAll().observeAndAwait()
        assertThat(adventureBooks.size, `is`(2))
        assertThat(adventureBooks[0].book.id, `is`(2L))
    }
}
