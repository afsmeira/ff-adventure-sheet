package pt.afsmeira.ffadventuresheet.db

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pt.afsmeira.ffadventuresheet.model.Stat

@RunWith(JUnit4::class)
class InitialStateTest {

    @Test
    fun booksTest() {
        assertThat(InitialState.books.size, `is`(69))
    }

    @Test
    fun statPossibleValuesTest() {
        val underTest = InitialState.stats.filter { stat ->
            (stat.type == Stat.Type.INT || stat.type == Stat.Type.TEXT)
                    && stat.possibleValues is Stat.PossibleValues.Defined
        }
        assertThat(underTest.size, `is`(0))
    }

    @Test
    fun bookStatBooksTest() {
        // All books are referenced
        val books = InitialState.bookStats.distinctBy { it.bookId }
        assertThat(books.size, `is`(InitialState.books.size))

        // All stats are referenced
        val stats = InitialState.bookStats.distinctBy { it.statId }
        assertThat(stats.size, `is`(InitialState.stats.size))
    }
}
