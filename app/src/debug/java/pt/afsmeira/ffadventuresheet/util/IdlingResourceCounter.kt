package pt.afsmeira.ffadventuresheet.util

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Application-wide counter for resources that are doing tasks in background threads. This class is
 * only useful for testing purposes, but its implementation and overall usage must be in non-test
 * packages.
 *
 * See [android documentation](https://developer.android.com/training/testing/espresso/idling-resource).
 */
object IdlingResourceCounter {

    /**
     * The actual counter. **Should not be used directly except by test classes.**
     */
    @JvmField
    val counter = CountingIdlingResource("GLOBAL")

    fun increment() = synchronized(counter) {
        counter.increment()
    }

    fun decrement() = synchronized(counter) {
        if(!counter.isIdleNow) counter.decrement()
    }
}
