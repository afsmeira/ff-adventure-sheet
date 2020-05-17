package pt.afsmeira.ffadventuresheet.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import pt.afsmeira.ffadventuresheet.BuildConfig
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.ui.adapter.StatAdapter
import pt.afsmeira.ffadventuresheet.ui.viewmodel.AdventureStatViewModel

/**
 * Activity to play an [Adventure].
 *
 * This activity expects an [Adventure] as an extra in the intent, under the key
 * [ADVENTURE_INTENT_KEY].
 *
 * ## Activity lifecycle
 *                 +------------------------+
 *                 |   AdventuresActivity   |
 *                 +------------------------+
 *                        |          ^
 *       Select Adventure |          | Back button
 *                        v          |
 *                 +------------------------+
 *                 |                        |
 *                 | AdventureSheetActivity |
 *                 |                        |
 *                 +------------------------+
 *                        ^
 *                        | Create new adventure
 *                        |
 *                 +------------------------+
 *                 |  NewCharacterActivity  |
 *                 +------------------------+
 *
 * @throws IllegalStateException When there is no [Adventure] data in the intent.
 */
class AdventureSheetActivity : AppCompatActivity() {

    private lateinit var adventure: Adventure

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adventure_sheet)

        adventure = Gson().fromJson(
            intent.extras?.getString(ADVENTURE_INTENT_KEY),
            Adventure::class.java
        ) ?: throw IllegalStateException("Intent does not have adventure data")

        val adventureStatsList =
            findViewById<RecyclerView>(R.id.activity_adventure_sheet_stat_list).apply {
                // TODO Should the following properties be set on the layout file?
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@AdventureSheetActivity)
            }

        val adventureStatsViewModel: AdventureStatViewModel by viewModels {
            AdventureStatViewModel.Factory(application, adventure)
        }
        adventureStatsViewModel.data.observe(this, Observer { adventureStatStats ->
            adventureStatsList.adapter =
                StatAdapter(adventureStatStats.map { it.toTyped().typedStat }, lifecycleScope)
        })
    }

    companion object {

        /**
         * The key for retrieving the expected [Adventure] from the intent passed to this activity.
         */
        const val ADVENTURE_INTENT_KEY = "${BuildConfig.APPLICATION_ID}.adventure"
    }
}
