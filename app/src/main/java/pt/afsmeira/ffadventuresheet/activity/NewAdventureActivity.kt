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

class NewAdventureActivity : AppCompatActivity() {

    // TODO These two listeners can be created in the `onCreate` method
    private val newAdventureClickListener = object : NewAdventureClickListener {
        override fun onNewAdventureClick(book: Book) {
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
    }

    private val bookClickListener = object : BookClickListener {
        override fun onBookClick(book: Book) {
            NewAdventureDialogFragment(book, newAdventureClickListener)
                .show(supportFragmentManager, NewAdventureDialogFragment.TAG)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_adventure)

        val bookGrid = findViewById<RecyclerView>(R.id.book_grid).apply {
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
}