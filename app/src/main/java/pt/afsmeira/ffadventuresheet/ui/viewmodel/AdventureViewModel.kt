package pt.afsmeira.ffadventuresheet.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.AdventureBook
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * [DataViewModel] for [AdventureBook] data.
 */
class AdventureViewModel(application: Application)
    : DataViewModel<Array<AdventureBook>>(application) {

    /**
     * [LiveData] for all [Adventure]s (and corresponding [Book]s) in the DB.
     */
    override fun fetchData(): LiveData<Array<AdventureBook>> =
        FFAdventureSheetDatabase.get(getApplication()).adventureDao().listAll()
}
