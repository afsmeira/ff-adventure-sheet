package pt.afsmeira.ffadventuresheet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.util.IdlingResourceCounter

/**
 * [DataViewModel] for [Book] data.
 *
 * [Book] data is immutable and only changes (potentially) between application versions.
 */
class BookViewModel(application: Application) : DataViewModel<Array<Book>>(application) {

    /**
     * [LiveData] for all [Book]s in the DB.
     */
    override fun fetchData(): LiveData<Array<Book>> =
        FFAdventureSheetDatabase.get(getApplication()).bookDao().listAll()
}
