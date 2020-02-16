package pt.afsmeira.ffadventuresheet.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Book

class BooksViewModel(application: Application) : AndroidViewModel(application) {

    val books: LiveData<Array<Book>> by lazy {
        liveData {
            emit(getBooks(getApplication()))
        }
    }

    private suspend fun getBooks(context: Context) = withContext(Dispatchers.IO) {
        FFAdventureSheetDatabase.get(context).bookDao().listAll()
    }
}