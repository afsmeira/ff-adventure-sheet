package pt.afsmeira.ffadventuresheet.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.AdventureBook
import java.text.SimpleDateFormat
import java.util.Date

/**
 * [DataAdapter] for displaying an array of [AdventureBook]s, i.e. an adventure and the
 * corresponding book.
 */
class AdventureAdapter(
    adventures: Array<AdventureBook>,
    adventureClickListener: ClickListener<AdventureBook>
) : DataAdapter<AdventureBook>(adventures, adventureClickListener) {

    /**
     * The view holder for an [AdventureBook], consisting of an [ImageView] for the book cover and
     * [TextView]s for the book name and other adventure meta information.
     */
    class AdventureView(
        self: android.view.View,
        private val bookCover: ImageView,
        private val bookName: TextView,
        private val lastPlayedAt: TextView,
        private val lastParagraph: TextView
    ) : DataAdapter.View<AdventureBook>(self) {

        override fun bind(dataItem: AdventureBook) {
            Picasso.get()
                .load(Uri.parse(dataItem.book.coverUrl))
                .placeholder(R.drawable.ic_launcher_background) // TODO Use dedicated image
                .error(R.drawable.ic_launcher_foreground) // TODO Use dedicated image
                .into(bookCover)

            bookName.text = dataItem.book.name
            lastPlayedAt.text =
                lastPlayedAt.context.getString(
                    R.string.view_adventure_last_played_at,
                    SimpleDateFormat.getDateTimeInstance().format(
                        Date.from(dataItem.adventure.updatedAt)
                    )
                )
            lastParagraph.text =
                lastParagraph.context.getString(
                    R.string.view_adventure_last_paragraph,
                    dataItem.adventure.lastParagraph
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdventureView {
        val adventureView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adventure, parent, false)
        val bookCover = adventureView.findViewById<ImageView>(R.id.view_adventure_book_cover)
        val bookName = adventureView.findViewById<TextView>(R.id.view_adventure_book_name)
        val lastPlayedAt = adventureView.findViewById<TextView>(R.id.view_adventure_last_played_at)
        val lastParagraph = adventureView.findViewById<TextView>(R.id.view_adventure_last_paragraph)

        return AdventureView(adventureView, bookCover, bookName, lastPlayedAt, lastParagraph)
    }
}
