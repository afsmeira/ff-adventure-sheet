package pt.afsmeira.ffadventuresheet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
 */
class AdventuresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adventures)

        val adventureClickListener = object : DataAdapter.View.ClickListener<AdventureBook> {
            override fun onDataItemClicked(dataItem: AdventureBook) {
                // TODO Temporary code. This should start the AdventureSheet activity
                Toast
                    .makeText(this@AdventuresActivity, dataItem.book.name, Toast.LENGTH_LONG)
                    .show()
            }
        }

        val adventureList = findViewById<RecyclerView>(R.id.activity_adventures_list).apply {
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
}
