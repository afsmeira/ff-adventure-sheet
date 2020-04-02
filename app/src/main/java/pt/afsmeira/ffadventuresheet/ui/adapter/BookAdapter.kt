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
 * [DataAdapter] for displaying a list of [Book]s.
 *
 * @param books The data to display.
 * @param bookClickListener The listener to be called whenever a displayed data item is clicked.
 */
class BookAdapter(
    books: List<Book>,
    private val bookClickListener: View.ClickListener<Book>
) : DataAdapter<Book>(books) {

    /**
     * The view holder for a [Book].
     *
     * @param self The view representing the complete view holder.
     * @param cover An image view with the book's cover.
     * @param name A text view with the book's name.
     * @param bookClickListener The listener to be called when [self] is clicked.
     */
    class BookView(
        self: android.view.View,
        private val cover: ImageView,
        private val name: TextView,
        bookClickListener: ClickListener<Book>
    ) : DataAdapter.View<Book>(self, bookClickListener) {

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

        val cover: ImageView = bookView.findViewById(R.id.view_book_cover)
        val name: TextView = bookView.findViewById(R.id.view_book_name)

        return BookView(bookView, cover, name, bookClickListener)
    }
}
