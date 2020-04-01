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
import pt.afsmeira.ffadventuresheet.model.AdventureStat
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.StatAdapter
import pt.afsmeira.ffadventuresheet.ui.viewmodel.StatViewModel

/**
 * Activity to create a new [Adventure], by first setting the [Stat]s of the character being
 * created.
 *
 * This activity displays a list of [Stat]s that can be configured, or selected, to then define the
 * [AdventureStat]s of the character for the Adventure to be created.
 */
class NewCharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)
        setTitle(R.string.activity_new_character_title)

        val book = Gson().fromJson(
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
                StatAdapter(stats.map { it.toTyped() }.toTypedArray(), lifecycleScope)
        })
    }

    companion object {

        /**
         * The key for retrieving the expected [Book] from the intent passed to this activity.
         */
        const val BOOK_INTENT_KEY = "${BuildConfig.APPLICATION_ID}.book"
    }
}
