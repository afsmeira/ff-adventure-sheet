package pt.afsmeira.ffadventuresheet.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.AdventureBook
import pt.afsmeira.ffadventuresheet.ui.adapter.AdventureAdapter
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter
import pt.afsmeira.ffadventuresheet.ui.dialog.DeleteAdventureDialogFragment
import pt.afsmeira.ffadventuresheet.ui.viewmodel.AdventureViewModel
import pt.afsmeira.ffadventuresheet.util.IdlingResourceCounter

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

        val deleteAdventureClickListener = object : DeleteAdventureDialogFragment.DeleteAdventureClickListener {
            override fun onDeleteAdventureClick(adventure: Adventure) {
                deleteAdventure(adventure)
            }
        }

        val adventureLongClickListener = object : DataAdapter.View.LongClickListener<AdventureBook> {
            override fun onDataItemLongClicked(dataItem: AdventureBook) {
                DeleteAdventureDialogFragment(dataItem.adventure, deleteAdventureClickListener)
                    .show(supportFragmentManager, DeleteAdventureDialogFragment.TAG)
            }
        }

        val adventureClickListener = object : DataAdapter.View.ClickListener<AdventureBook> {
            override fun onDataItemClicked(dataItem: AdventureBook) {
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
            adventureList.adapter =
                AdventureAdapter(adventures, adventureClickListener, adventureLongClickListener)
        })

        val newAdventureButton =
            findViewById<FloatingActionButton>(R.id.activity_adventures_new_adventure)
        newAdventureButton.setOnClickListener {
            startNewAdventureActivity()
        }
    }

    private fun startNewAdventureActivity() =
        startActivity(Intent(this, NewAdventureActivity::class.java))


    private fun deleteAdventure(adventure: Adventure) {
        IdlingResourceCounter.increment()

        lifecycleScope.launch(Dispatchers.IO) {
            FFAdventureSheetDatabase
                .get(this@AdventuresActivity)
                .adventureDao()
                .delete(adventure)
        }
    }
}
