package pt.afsmeira.ffadventuresheet.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.adapter.BookAdapter
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.viewmodel.BooksViewModel

class NewAdventureActivity : AppCompatActivity() {

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
            bookGrid.adapter = BookAdapter(books)
        })

    }
}