package pt.afsmeira.ffadventuresheet.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.afsmeira.ffadventuresheet.BuildConfig
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.AdventureStat
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.StatAdapter
import pt.afsmeira.ffadventuresheet.ui.viewmodel.StatViewModel
import pt.afsmeira.ffadventuresheet.util.IdlingResourceCounter

/**
 * Activity to create a new [Adventure], by first setting the [Stat]s of the character being
 * created.
 *
 * This activity displays a list of [Stat]s that can be configured, or selected, to then define the
 * [AdventureStat]s of the character for the Adventure to be created.
 */
class NewCharacterActivity : AppCompatActivity() {

    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        setSupportActionBar(findViewById(R.id.activity_new_character_action_bar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.activity_new_character_title)

        book = Gson().fromJson(
            intent.extras?.getString(BOOK_INTENT_KEY),
            Book::class.java
        ) ?: throw IllegalStateException("Intent does not have book data")

        val statsList = findViewById<RecyclerView>(R.id.activity_new_character_stat_list).apply {
            // TODO Should the following properties be set on the layout file?
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@NewCharacterActivity)
        }

        val statViewModel: StatViewModel by viewModels { StatViewModel.Factory(application, book) }
        statViewModel.data.observe(this, Observer { stats ->
            statsList.adapter =
                StatAdapter(stats.map { it.toTyped() }, lifecycleScope)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_activity_new_character, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            R.id.action_bar_activity_new_character_create -> {
                createNewAdventureAndFinishActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    /**
     * Create a new adventure, with the stats defined in this activity. Then, finish this activity.
     *
     * The actual creation occurs on a coroutine, running on the lifecycle scope of this activity.
     */
    private fun createNewAdventureAndFinishActivity() {
        IdlingResourceCounter.increment()

        lifecycleScope.launch(Dispatchers.IO) {
            val statsAdapter =
                findViewById<RecyclerView>(
                    R.id.activity_new_character_stat_list
                ).adapter as StatAdapter

            FFAdventureSheetDatabase
                .get(this@NewCharacterActivity)
                .adventureDao()
                .create(book.id, statsAdapter.data)

            setResult(Activity.RESULT_OK)
            // TODO Launch the AdventureActivity before finishing this activity
            finish()

            IdlingResourceCounter.decrement()
        }
    }


    companion object {

        /**
         * The key for retrieving the expected [Book] from the intent passed to this activity.
         */
        const val BOOK_INTENT_KEY = "${BuildConfig.APPLICATION_ID}.book"
    }
}
