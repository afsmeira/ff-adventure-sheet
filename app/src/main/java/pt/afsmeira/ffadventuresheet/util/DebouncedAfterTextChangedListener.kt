package pt.afsmeira.ffadventuresheet.util

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration

/**
 * A [TextWatcher] that only implements [afterTextChanged]. When that function is called, [listener]
 * is called, but using a debounce mechanism.
 *
 * @param wait The duration to wait for each [listener] call.
 * @param coroutineScope The scope where the debouncing mechanism calls will execute.
 * @param listener The function to execute when [afterTextChanged] fires, subject to debounce.
 */
class DebouncedAfterTextChangedListener(
    wait: Duration = Duration.ofMillis(300L),
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    private val listener: (String) -> Unit
) : TextWatcher {

    /**
     * Creates a function that debounces its calls to [destinationFunction].
     *
     * @param wait How long to wait before callin [destinationFunction].
     * @param coroutineScope The scope where [destinationFunction] will be executed.
     * @param destinationFunction The function to execute.
     */
    private fun <T> debounce(
        wait: Duration,
        coroutineScope: CoroutineScope,
        destinationFunction: (T) -> Unit
    ): (T) -> Unit {
        var debounceJob: Job? = null
        return { param: T ->
            debounceJob?.cancel()
            debounceJob = coroutineScope.launch {
                delay(wait.toMillis())
                destinationFunction(param)
            }
        }
    }

    private val debouncedListener = debounce<String>(wait, coroutineScope) { listener(it) }

    override fun afterTextChanged(s: Editable?) = debouncedListener(s.toString())

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
