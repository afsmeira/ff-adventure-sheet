package pt.afsmeira.ffadventuresheet.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.afsmeira.ffadventuresheet.dialog.NewAdventureDialogFragment
import pt.afsmeira.ffadventuresheet.dialog.NewAdventureDialogFragment.NewAdventureClickListener
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.adapter.BookAdapter
import pt.afsmeira.ffadventuresheet.adapter.DataAdapter
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.viewmodel.BookViewModel
import java.time.Instant

/**
 * Activity to create a new [Adventure].
 *
 * This activity displays a grid of [Book]s that can be selected to create a new [Adventure].
 *
 * This activity can only be started from [AdventuresActivity] and can transition back to it.
 * // TODO Complement documentation with the activities this view can transition to/from.
 */
class NewAdventureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_adventure)
        setTitle(R.string.activity_new_adventure_title)

        val newAdventureClickListener = object : NewAdventureClickListener {
            override fun onNewAdventureClick(book: Book) {
                createNewAdventure(book)

                // TODO Temporary code. The correct flow will be to launch the CharacterCreation activity
                finish()
            }
        }

        val bookClickListener = object : DataAdapter.ClickListener<Book> {
            override fun onDataItemClicked(dataItem: Book) {
                showNewAdventureDialog(dataItem, newAdventureClickListener)
            }
        }

        val bookGrid = findViewById<RecyclerView>(R.id.activity_new_adventure_book_grid).apply {
            // TODO Should the following properties be set on the layout file?
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@NewAdventureActivity, 2)
        }

        val bookViewModel: BookViewModel by viewModels()
        bookViewModel.data.observe(this, Observer { books ->
            bookGrid.adapter = BookAdapter(books, bookClickListener)
        })
    }

    /**
     * Show a [NewAdventureDialogFragment] for `book`.
     */
    private fun showNewAdventureDialog(
        book: Book,
        newAdventureClickListener: NewAdventureClickListener
    ) =
        NewAdventureDialogFragment(book, newAdventureClickListener)
            .show(supportFragmentManager, NewAdventureDialogFragment.TAG)

    /**
     * Create and persist a new [Adventure] for `book` in a lifecycle aware co-routine.
     */
    private fun createNewAdventure(book: Book) =
        lifecycleScope.launch(Dispatchers.IO) {
            val adventure = Adventure(
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
                bookId = book.id
            )

            FFAdventureSheetDatabase
                .get(this@NewAdventureActivity)
                .adventureDao()
                .create(adventure)
        }
}
