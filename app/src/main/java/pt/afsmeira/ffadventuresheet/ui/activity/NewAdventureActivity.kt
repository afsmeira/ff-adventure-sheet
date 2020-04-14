package pt.afsmeira.ffadventuresheet.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Adventure
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.ui.adapter.BookAdapter
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter
import pt.afsmeira.ffadventuresheet.ui.dialog.NewAdventureDialogFragment
import pt.afsmeira.ffadventuresheet.ui.dialog.NewAdventureDialogFragment.NewAdventureClickListener
import pt.afsmeira.ffadventuresheet.ui.viewmodel.BookViewModel

/**
 * Activity to create a new [Adventure].
 *
 * This activity displays a grid of [Book]s that can be selected to create a new [Adventure].
 *
 * ## Activity lifecycle
 *                  +----------------------+
 *                  |  AdventuresActivity  |
 *                  +----------------------+
 *                        |          ^
 *          New Adventure |          | Back button
 *                        v          |
 *                  +----------------------+
 *                  |                      |
 *                  | NewAdventureActivity |
 *                  |                      |
 *                  +----------------------+
 *                        |          ^
 *      Confirm adventure |          | Back button
 *                        v          |
 *                  +----------------------+
 *                  | NewCharacterActivity |
 *                  +----------------------+
 *
 * Once [NewCharacterActivity] finishes successfully, this activity also finishes.
 */
class NewAdventureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_adventure)
        setTitle(R.string.activity_new_adventure_title)

        val newAdventureClickListener = object : NewAdventureClickListener {
            override fun onNewAdventureClick(book: Book) {
                startNewCharacterActivity(book)
            }
        }

        val bookClickListener = object : DataAdapter.View.ClickListener<Book> {
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
     * Start [NewCharacterActivity] for `book`.
     */
    private fun startNewCharacterActivity(book: Book) {
        val intent = Intent(this, NewCharacterActivity::class.java).apply {
            putExtra(NewCharacterActivity.BOOK_INTENT_KEY, Gson().toJson(book))
        }
        startActivityForResult(intent, REQUEST_CODE)
    }

    /**
     * Finish current activity if the [NewCharacterActivity] launched by this activity, finishes
     * with a success value.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            finish()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {

        /**
         * Code this activity uses when starting other activities.
         */
        const val REQUEST_CODE = 100
    }
}
