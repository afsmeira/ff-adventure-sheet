package pt.afsmeira.ffadventuresheet.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.adapter.AdventureAdapter
import pt.afsmeira.ffadventuresheet.adapter.DataAdapter
import pt.afsmeira.ffadventuresheet.model.AdventureBook
import pt.afsmeira.ffadventuresheet.viewmodel.AdventureViewModel

class AdventuresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("AdventuresActivity", "CREATED")

        setContentView(R.layout.activity_adventures)

        val adventureClickListener = object : DataAdapter.ClickListener<AdventureBook> {
            override fun onDataItemClicked(dataItem: AdventureBook) {
                Toast.makeText(this@AdventuresActivity, dataItem.book.name, Toast.LENGTH_LONG).show()
            }
        }

        val adventureList = findViewById<RecyclerView>(R.id.activity_adventures_list).apply {
            // TODO Should the following properties be set on the layout file?
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@AdventuresActivity)
        }

        val adventuresViewModel: AdventureViewModel by viewModels()
        adventuresViewModel.adventures.observe(this, Observer<Array<AdventureBook>> { adventures ->
            adventureList.adapter = AdventureAdapter(adventures, adventureClickListener)
        })

        val newAdventureButton = findViewById<FloatingActionButton>(R.id.activity_adventures_new_adventure)
        newAdventureButton.setOnClickListener {
            val newAdventureActivityIntent = Intent(this, NewAdventureActivity::class.java)
            startActivity(newAdventureActivityIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.w("AdventuresActivity", "STARTED")
    }

    override fun onResume() {
        super.onResume()
        Log.w("AdventuresActivity", "RESUMED")
    }

    override fun onRestart() {
        super.onRestart()
        Log.w("AdventuresActivity", "RESTARTED")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("AdventuresActivity", "DESTROYED")
    }

    override fun onStop() {
        super.onStop()
        Log.w("AdventuresActivity", "STOPPED")
    }

    override fun onPause() {
        super.onPause()
        Log.w("AdventuresActivity", "PAUSED")
    }
}