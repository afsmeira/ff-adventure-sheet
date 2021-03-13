package pt.afsmeira.ffadventuresheet.db

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class InitialStateTest {

    @Test
    fun booksTest() {
        assertThat(InitialState.books.size, `is`(69))
    }
}
