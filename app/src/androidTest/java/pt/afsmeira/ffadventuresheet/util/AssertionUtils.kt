package pt.afsmeira.ffadventuresheet.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.BaseMatcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.greaterThan

object AssertionUtils {

    /**
     * Asserts that a [RecyclerView] has [expectedItemCount] items.
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

    /**
     * Asserts that a [RecyclerView] has at least [expectedItemLowerBoundNonInclusive] items.
     *
     * @throws NoMatchingViewException if no view was found on which to perform this assertion.
     * @throws ClassCastException if the view found is not a [RecyclerView].
     */
    fun recyclerViewHasItemCountGreaterThan(expectedItemLowerBoundNonInclusive: Int) =
        ViewAssertion { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            assertThat(
                (view as RecyclerView).adapter?.itemCount,
                greaterThan(expectedItemLowerBoundNonInclusive)
            )
        }

    /**
     * Returns the first view matching [itemMatcher].
     *
     * **NOTE:** When you perform more than one action on a matched view, the matcher is invoked for
     * each action. Due to the stateful nature of this matcher, you will need to create a new
     * instance every time you want to perform an action on the matched view.
     */
    fun firstMatching(itemMatcher: Matcher<View>): Matcher<View> =
        object : BaseMatcher<View>() {
            var isFirstMatch = true

            override fun describeTo(description: Description?) {
                description?.appendText("first match of: ")
                itemMatcher.describeTo(description)
            }

            override fun matches(item: Any?): Boolean =
                (itemMatcher.matches(item) && isFirstMatch).also {
                    if (it) {
                        isFirstMatch = false
                    }
                }
        }
}
