package pt.afsmeira.ffadventuresheet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.AdventureBook
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * [AndroidViewModel] for [AdventureBook] data.
 */
class AdventureViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * [LiveData] for all [Adventure]s (and corresponding [Book]s) in the DB.
     */
    val adventures: LiveData<Array<AdventureBook>> by lazy {
        FFAdventureSheetDatabase.get(getApplication()).adventureDao().listAll()
    }
}
