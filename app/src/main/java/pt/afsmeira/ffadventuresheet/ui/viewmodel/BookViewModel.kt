package pt.afsmeira.ffadventuresheet.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * [DataViewModel] for [Book] data.
 *
 * [Book] data is immutable and only changes (potentially) between application versions.
 */
class BookViewModel(application: Application) : DataViewModel<List<Book>>(application) {

    /**
     * [LiveData] for all [Book]s in the DB.
     */
    override fun fetchData(): LiveData<List<Book>> =
        liveData(Dispatchers.IO) {
            emit(
                FFAdventureSheetDatabase.get(getApplication<Application>()).bookDao().listAll()
            )
        }
}
