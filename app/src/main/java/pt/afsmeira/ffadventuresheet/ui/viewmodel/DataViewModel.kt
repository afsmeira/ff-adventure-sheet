package pt.afsmeira.ffadventuresheet.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pt.afsmeira.ffadventuresheet.util.IdlingResourceCounter

/**
 * [AndroidViewModel] for data of type `T`.
 *
 * Extending classes will have the underlying data lazily available in [data].
 */
abstract class DataViewModel<T>(application: Application) : AndroidViewModel(application) {

    /**
     * Fetch the data, wrapped in a [LiveData] object.
     */
    abstract fun fetchData(): LiveData<T>

    /**
     * The actual data, wrapped in a [lazy] construct.
     */
    val data: LiveData<T> by lazy {
        IdlingResourceCounter.increment()
        fetchData().also {
            IdlingResourceCounter.decrement()
        }
    }
}
