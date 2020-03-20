package pt.afsmeira.ffadventuresheet.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * [DataAdapter] for displaying an array of [Book]s.
 */
class BookAdapter(
    books: Array<Book>,
    bookClickListener: ClickListener<Book>
) : DataAdapter<Book>(books, bookClickListener) {

    /**
     * The view holder for a [Book], consisting of an [ImageView] for the cover and a [TextView] for
     * the book name.
     */
    class BookView(
        self: android.view.View,
        private val cover: ImageView,
        private val name: TextView
    ) : DataAdapter.View<Book>(self) {

        override fun bind(dataItem: Book) {
            name.text = dataItem.name
            Picasso.get()
                .load(Uri.parse(dataItem.coverUrl))
                .placeholder(R.drawable.ic_launcher_background) // TODO Use dedicated image
                .error(R.drawable.ic_launcher_foreground) // TODO Use dedicated image
                .into(cover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookView {
        val bookView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_book, parent, false)
        val cover = bookView.findViewById<ImageView>(R.id.view_book_cover)
        val name = bookView.findViewById<TextView>(R.id.view_book_name)

        return BookView(bookView, cover, name)
    }
}
