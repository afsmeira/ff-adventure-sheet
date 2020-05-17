package pt.afsmeira.ffadventuresheet.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.AdventureStatStat

/**
 * [DataViewModel] for [AdventureStatStat] data for [adventure].
 */
class AdventureStatViewModel(
    application: Application,
    private val adventure: Adventure
) : DataViewModel<List<AdventureStatStat>>(application) {

    /**
     * [LiveData] for all [AdventureStatStat]s for [adventure].
     */
    override fun fetchData(): LiveData<List<AdventureStatStat>> =
        liveData(Dispatchers.IO) {
            emit(
                FFAdventureSheetDatabase
                    .get(getApplication<Application>())
                    .statDao()
                    .listForAdventure(adventure.id)
            )
        }

    /**
     * Factory for [AdventureStatViewModel].
     *
     * This class is necessary because [AdventureStatViewModel]'s constructor takes a parameter
     * (besides [application]).
     */
    class Factory(
        private val application: Application,
        private val adventure: Adventure
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            AdventureStatViewModel(application, adventure) as T
    }
}
