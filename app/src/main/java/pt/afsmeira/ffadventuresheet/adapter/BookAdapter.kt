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
    private val bookList: Array<Book>
) : RecyclerView.Adapter<BookAdapter.BookView>() {

    class BookView(
        val book: View,
        val cover: ImageView,
        val name: TextView
    ) : RecyclerView.ViewHolder(book)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookView {
        val book = LayoutInflater.from(parent.context).inflate(R.layout.book, parent, false)
        val bookCover = book.findViewById<ImageView>(R.id.book_cover)
        val bookName = book.findViewById<TextView>(R.id.book_name)

        return BookView(book, bookCover, bookName)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookView, position: Int) {
        holder.name.text = bookList[position].name
        Picasso.get()
            .load(Uri.parse(bookList[position].coverUrl))
            .resize(200, 326)
            .centerCrop()
            .into(holder.cover)
    }
}