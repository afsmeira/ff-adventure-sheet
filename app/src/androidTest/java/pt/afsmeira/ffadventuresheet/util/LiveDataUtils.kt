package pt.afsmeira.ffadventuresheet.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Wait the specified amount of time, or 2 seconds by default, for this [LiveData] to produce a
 * value.
 *
 * @throws TimeoutException If no value is produced after the timeout.
 */
fun <T> LiveData<T>.observeAndAwait(
    timeout: Long = 2L,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {

    var data: T? = null
    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@observeAndAwait.removeObserver(this)
        }
    }
    observeForever(observer)

    if (!latch.await(timeout, timeUnit)) {
        removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }

    // At this point we can guarantee that data is not null, so it's ok to cast
    @Suppress("UNCHECKED_CAST")
    return data as T
}
