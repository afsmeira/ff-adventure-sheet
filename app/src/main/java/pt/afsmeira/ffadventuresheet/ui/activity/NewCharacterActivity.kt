package pt.afsmeira.ffadventuresheet.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
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
import pt.afsmeira.ffadventuresheet.ui.view.StatView
import pt.afsmeira.ffadventuresheet.ui.viewmodel.StatViewModel
import pt.afsmeira.ffadventuresheet.util.IdlingResourceCounter
import pt.afsmeira.ffadventuresheet.util.StatProcessor
import java.time.Instant

/**
 * Activity to create a new [Adventure], by first setting the [Stat]s of the character being
 * created.
 *
 * This activity displays a list of [Stat]s that can be configured, or selected, to then define the
 * [AdventureStat]s of the character for the Adventure to be created.
 *
 * This activity expects a [Book] as an extra in the intent, under the key [BOOK_INTENT_KEY].
 *
 * ## Activity lifecycle
 *                 +------------------------+
 *                 |  NewAdventureActivity  |
 *                 +------------------------+
 *                        |          ^
 *            Select Book |          | Back button
 *                        v          |
 *                 +------------------------+
 *                 |                        |
 *                 |  NewCharacterActivity  |
 *                 |                        |
 *                 +------------------------+
 *                        |
 *                        | Create new adventure
 *                        v
 *                 +------------------------+
 *                 | AdventureSheetActivity |
 *                 +------------------------+
 *
 * @throws IllegalStateException When there is no [Book] data in the intent.
 */
class NewCharacterActivity : AppCompatActivity() {

    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        book = Gson().fromJson(
            intent.extras?.getString(BOOK_INTENT_KEY),
            Book::class.java
        ) ?: throw IllegalStateException("Intent does not have book data")

        setSupportActionBar(findViewById(R.id.activity_new_character_action_bar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = book.name

        val statsList =
            findViewById<LinearLayout>(R.id.activity_new_character_stat_list)

        val statViewModel: StatViewModel by viewModels { StatViewModel.Factory(application, book) }
        statViewModel.data.observe(this, Observer { stats ->
            statsList.removeAllViews()
            StatProcessor.generateViews(this, stats).forEach { v -> statsList.addView(v) }

            // Only create listener after having all the stat data
            val createCharacterButton = findViewById<Button>(R.id.activity_new_character_create)
            // Setting the listener replaces existing ones
            createCharacterButton.setOnClickListener {
                val characterName =
                    findViewById<TextView>(R.id.view_character_name_value).text.toString()
                val setupStats = StatProcessor.getValues(statsList)
                val nonSetupStats = stats.filter { s -> !s.setup }
                createNewAdventureWorkflow(characterName, setupStats, nonSetupStats)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_activity_new_character, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            R.id.action_bar_activity_new_character_create -> {
                // TODO Re-roll stats
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    /**
     * Starts the workflow for creating a new adventure which includes:
     *
     * - Create the actual adventure
     * - Start a [AdventureSheetActivity] to play that adventure
     * - Finish this activity
     *
     * The workflow executes on a coroutine, running on the lifecycle scope of this activity.
     */
    private fun createNewAdventureWorkflow(
        characterName: String,
        setupStats: List<StatView>,
        nonSetupStats: List<Stat>
    ) {
        IdlingResourceCounter.increment()

        lifecycleScope.launch(Dispatchers.IO) {
            val adventure = createAdventure(characterName, setupStats, nonSetupStats)
            startAdventureSheetActivity(adventure)
            finishActivity()

            IdlingResourceCounter.decrement()
        }
    }

    /**
     * Create a new adventure, with the stats defined in this activity, and then return it.
     */
    private suspend fun createAdventure(
        characterName: String,
        setupStats: List<StatView>,
        nonSetupStats: List<Stat>
    ): Adventure {
        val adventure = Adventure(
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            bookId = book.id,
            characterName = characterName
        )

        val adventureStats = AdventureStat.create(book.id, setupStats, nonSetupStats)

        FFAdventureSheetDatabase
            .get(this@NewCharacterActivity)
            .adventureDao()
            .create(adventure, adventureStats)

        return adventure
    }

    /**
     * Start [AdventureSheetActivity] for [adventure].
     */
    private fun startAdventureSheetActivity(adventure: Adventure) {
        // TODO: Create intent and start AdventureSheetActivity
    }

    /**
     * Finish this activity.
     */
    private fun finishActivity() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    companion object {

        /**
         * The key for retrieving the expected [Book] from the intent passed to this activity.
         */
        const val BOOK_INTENT_KEY = "${BuildConfig.APPLICATION_ID}.book"
    }
}
