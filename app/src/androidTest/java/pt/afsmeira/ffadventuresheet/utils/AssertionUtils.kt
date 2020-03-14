package pt.afsmeira.ffadventuresheet.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers.`is`

object AssertionUtils {

    /**
     * Asserts that a [RecyclerView] has `expectedItemCount` items.
     *
     * @throws NoMatchingViewException if no view was found on which to perform this assertion.
     * @throws ClassCastException if the view found is not a [RecyclerView].
     */
    fun recyclerViewHasItemCount(expectedItemCount: Int) =
        ViewAssertion { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            assertThat(
                (view as RecyclerView).adapter?.itemCount,
                `is`(expectedItemCount)
            )
        }
}
