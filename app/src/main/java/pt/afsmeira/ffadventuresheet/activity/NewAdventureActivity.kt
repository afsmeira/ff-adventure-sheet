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
import pt.afsmeira.ffadventuresheet.adapter.BookAdapter.BookClickListener
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.viewmodel.BooksViewModel
import java.time.Instant

/**
 * Activity to create a new [Adventure].
 *
 * This activity displays a grid of [Book]s that can be selected to create a new [Adventure].
 *
 * // TODO Complement documentation with the activities this view can transition to/from.
 */
class NewAdventureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_adventure)

        val newAdventureClickListener = object : NewAdventureClickListener {
            override fun onNewAdventureClick(book: Book) {
                createNewAdventure(book)
            }
        }

        val bookClickListener = object : BookClickListener {
            override fun onBookClick(book: Book) {
                showNewAdventureDialog(book, newAdventureClickListener)
            }
        }

        val bookGrid = findViewById<RecyclerView>(R.id.activity_new_adventure_book_grid).apply {
            // TODO Should the following properties be set on the layout file?
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@NewAdventureActivity, 2)
        }

        val booksViewModel: BooksViewModel by viewModels()
        booksViewModel.books.observe(this, Observer<Array<Book>> { books ->
            // TODO Change underlying data and call notifyDataSetChanged() ?
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
     * Create and persist a new [Adventure] for `book`.
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

            // TODO Launch character creation activity
        }
}
