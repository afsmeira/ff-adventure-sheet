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

class BookAdapter(
    private val books: Array<Book>,
    private val bookClickListener: BookClickListener
) : RecyclerView.Adapter<BookAdapter.BookView>() {

    interface BookClickListener {
        fun onBookClick(book: Book)
    }

    class BookView(
        private val self: View,
        private val cover: ImageView,
        private val name: TextView
    ) : RecyclerView.ViewHolder(self) {

        fun bind(book: Book, bookClickListener: BookClickListener) {
            name.text = book.name
            Picasso.get()
                .load(Uri.parse(book.coverUrl))
                .resize(200, 326)
                .centerCrop()
                .into(cover)

            self.setOnClickListener {
                bookClickListener.onBookClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookView {
        val bookView = LayoutInflater.from(parent.context).inflate(R.layout.book, parent, false)
        val cover = bookView.findViewById<ImageView>(R.id.book_cover)
        val name = bookView.findViewById<TextView>(R.id.book_name)

        return BookView(bookView, cover, name)
    }

    override fun onBindViewHolder(holder: BookView, position: Int) =
        holder.bind(books[position], bookClickListener)

    override fun getItemCount(): Int = books.size
}