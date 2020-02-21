package pt.afsmeira.ffadventuresheet.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        liveData(Dispatchers.IO) {
            emit(getBooks(getApplication()))
        }
    }

    /**
     * Co-routine method for getting all [Book]s from the DB.
     */
    private suspend fun getBooks(context: Context): Array<Book> =
        FFAdventureSheetDatabase.get(context).bookDao().listAll()
}
