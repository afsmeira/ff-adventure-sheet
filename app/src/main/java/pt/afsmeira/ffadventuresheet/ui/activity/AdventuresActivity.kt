package pt.afsmeira.ffadventuresheet.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.AdventureBook
import pt.afsmeira.ffadventuresheet.ui.adapter.AdventureAdapter
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter
import pt.afsmeira.ffadventuresheet.ui.viewmodel.AdventureViewModel

/**
 * Activity to list [Adventure]s.
 *
 * This activity displays a list of [Adventure]s that can be selected and resumed. This activity
 * also provides a button to start new [Adventure]s.
 *
 * This activity is the application's main activity, the entry point. If can transition to and from
 * [NewAdventureActivity].
 *
 * ## Activity lifecycle
 *                                           Create New
 *                             |       -----------------------
 *          Application Starts |       |     Adventure       |
 *                             v       |                     |
 *                  +----------------------+                 v
 *                  |                      |     Back      +----------------------+
 *                  |  AdventuresActivity  |<--------------| NewAdventureActivity |
 *                  |                      |    Button     +----------------------+
 *                  +----------------------+
 *                        |         ^
 *       Select adventure |         | Back button
 *                        v         |
 *                 +------------------------+
 *                 | AdventureSheetActivity |
 *                 +------------------------+
 */
class AdventuresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adventures)

        val adventureClickListener = object : DataAdapter.View.ClickListener<AdventureBook> {
            override fun onDataItemClicked(dataItem: AdventureBook) {
                startAdventureSheetActivity(dataItem.adventure)
            }
        }

        val adventureList =
            findViewById<RecyclerView>(R.id.activity_adventures_list).apply {
                // TODO Should the following properties be set on the layout file?
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@AdventuresActivity)
            }

        val adventuresViewModel: AdventureViewModel by viewModels()
        adventuresViewModel.data.observe(this, Observer { adventures ->
            adventureList.adapter = AdventureAdapter(adventures, adventureClickListener)
        })

        val newAdventureButton =
            findViewById<FloatingActionButton>(R.id.activity_adventures_new_adventure)
        newAdventureButton.setOnClickListener {
            startNewAdventureActivity()
        }
    }

    private fun startNewAdventureActivity() =
        startActivity(Intent(this, NewAdventureActivity::class.java))

    /**
     * Start [AdventureSheetActivity] for [adventure].
     */
    private fun startAdventureSheetActivity(adventure: Adventure) {
        val intent = Intent(
            this@AdventuresActivity,
            AdventureSheetActivity::class.java
        ).apply {
            putExtra(
                AdventureSheetActivity.ADVENTURE_INTENT_KEY,
                Gson().toJson(adventure)
            )
        }
        startActivity(intent)
    }
}
