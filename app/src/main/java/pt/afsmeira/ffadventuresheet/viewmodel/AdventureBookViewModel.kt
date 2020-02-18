package pt.afsmeira.ffadventuresheet.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.AdventureBook
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * [AndroidViewModel] for [AdventureBook] data.
 */
class AdventureBookViewModel(application: Application) : AndroidViewModel(application) {

    // TODO This data will change over time. If the activity where this live data is used, is
    // recreated when navigating between activities, this view model will be recreated and the data
    // fetched again. If the activity is not recreated, but resumed instead, this live data will
    // need to be mutable.
    /**
     * [LiveData] for all [Adventure]s (and corresponding [Book]s) in the DB.
     */
    val adventureBooks: LiveData<Array<AdventureBook>> by lazy {
        liveData(Dispatchers.IO) {
            emit(getAdventureBooks(getApplication()))
        }
    }

    /**
     * Co-routine method for getting all [Adventure]s (and corresponding [Book]s) from the DB.
     */
    private suspend fun getAdventureBooks(context: Context): Array<AdventureBook> =
        FFAdventureSheetDatabase.get(context).adventureDao().listAll()
}