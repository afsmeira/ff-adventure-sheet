package pt.afsmeira.ffadventuresheet.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * Adapter for displaying an array of [Book]s.
 *
 * Callers using the adapter will be notified of a click on a [BookView] via the
 * `bookClickListener`.
 */
class BookAdapter(
    private val books: Array<Book>,
    private val bookClickListener: BookClickListener
) : RecyclerView.Adapter<BookAdapter.BookView>() {

    /**
     * Listener to notify callers of this adapter that a [BookView] was clicked.
     */
    interface BookClickListener {

        /**
         * Notify callers of which [Book] was bound to the clicked [BookView].
         */
        fun onBookClick(book: Book)
    }

    /**
     * The view holder for a [Book], consisting of an [ImageView] for the cover and a [TextView] for
     * the book name.
     */
    class BookView(
        private val self: View,
        private val cover: ImageView,
        private val name: TextView
    ) : RecyclerView.ViewHolder(self) {

        /**
         * Bind this view holder to the underlying `book` data and to a click listener.
         */
        fun bind(book: Book, bookClickListener: BookClickListener) {
            name.text = book.name
            Picasso.get()
                .load(Uri.parse(book.coverUrl))
                .resize(200, 326) // TODO Parametrize values
                .centerCrop()
                .into(cover)

            self.setOnClickListener {
                bookClickListener.onBookClick(book)
            }
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

    override fun onBindViewHolder(holder: BookView, position: Int) =
        holder.bind(books[position], bookClickListener)

    override fun getItemCount(): Int = books.size
}
