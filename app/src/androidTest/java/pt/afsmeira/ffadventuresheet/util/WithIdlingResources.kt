package pt.afsmeira.ffadventuresheet.util

import androidx.test.espresso.IdlingRegistry
import org.junit.After
import org.junit.Before

/**
 * Helper interface for tests that need to track idling resources, to prevent failures. It provides
 * methods for register and unregistering the application-wide [IdlingResourceCounter] before and
 * after each test, respectively.
 */
interface WithIdlingResources {

    @Before
    fun registerIdlingResourceCounter() {
        IdlingRegistry.getInstance().register(IdlingResourceCounter.counter)
    }

    @After
    fun unregisterIdlingResourceCounter() {
        IdlingRegistry.getInstance().unregister(IdlingResourceCounter.counter)
    }
}
