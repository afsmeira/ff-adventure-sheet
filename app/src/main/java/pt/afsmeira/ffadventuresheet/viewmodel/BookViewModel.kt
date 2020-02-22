package pt.afsmeira.ffadventuresheet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * [AndroidViewModel] for [Book] data.
 *
 * [Book] data is immutable and only changes (potentially) between application versions.
 */
class BookViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * [LiveData] for all [Book]s in the DB.
     */
    val books: LiveData<Array<Book>> by lazy {
        FFAdventureSheetDatabase.get(getApplication()).bookDao().listAll()
    }
}
