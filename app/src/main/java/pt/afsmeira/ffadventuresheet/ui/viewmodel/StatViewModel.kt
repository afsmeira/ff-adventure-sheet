package pt.afsmeira.ffadventuresheet.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * [DataViewModel] for [Stat] data for [book].
 */
class StatViewModel(
    application: Application,
    private val book: Book
) : DataViewModel<List<Stat>>(application) {

    /**
     * [LiveData] for all [Stat]s for [book].
     */
    override fun fetchData(): LiveData<List<Stat>> =
        liveData(Dispatchers.IO) {
            emit(
                FFAdventureSheetDatabase
                    .get(getApplication<Application>())
                    .statDao()
                    .listForBook(book.id)
            )
        }


    /**
     * Factory for [StatViewModel].
     *
     * This class is necessary because [StatViewModel]'s constructor takes a parameter (besides
     * `application`).
     */
    class Factory(
        private val application: Application,
        private val book: Book
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            StatViewModel(application, book) as T
    }
}
