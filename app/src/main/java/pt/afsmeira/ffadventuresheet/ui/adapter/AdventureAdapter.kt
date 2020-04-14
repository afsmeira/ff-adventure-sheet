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
 * [DataAdapter] for displaying a list of [AdventureBook]s, i.e. an adventure and the corresponding
 * book.
 *
 * @param adventures The data to display.
 * @param adventureClickListener The listener to be called whenever a displayed data item is
 *        clicked.
 */
class AdventureAdapter(
    adventures: List<AdventureBook>,
    private val adventureClickListener: View.ClickListener<AdventureBook>
) : DataAdapter<AdventureBook>(adventures) {

    /**
     * The view holder for an [AdventureBook].
     *
     * @param self The view representing the complete view holder.
     * @param bookCover An image view with the book's cover.
     * @param bookName A text view with the book's name.
     * @param lastPlayedAt A text view with the timestamp when this adventure was last played.
     * @param lastParagraph The last paragraph visited in the context of this adventure.
     * @param adventureClickListener The listener to be called when [self] is clicked.
     */
    class AdventureView(
        self: android.view.View,
        private val bookCover: ImageView,
        private val bookName: TextView,
        private val lastPlayedAt: TextView,
        private val lastParagraph: TextView,
        adventureClickListener: ClickListener<AdventureBook>
    ) : DataAdapter.View<AdventureBook>(self, adventureClickListener) {

        override fun bind(dataItem: AdventureBook) {
            Picasso.get()
                .load(Uri.parse(dataItem.book.coverUrl))
                // TODO Use appropriate images - https://github.com/afsmeira/ff-adventure-sheet/issues/28
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
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

        val bookCover: ImageView = adventureView.findViewById(R.id.view_adventure_book_cover)
        val bookName: TextView = adventureView.findViewById(R.id.view_adventure_book_name)
        val lastPlayedAt: TextView = adventureView.findViewById(R.id.view_adventure_last_played_at)
        val lastParagraph: TextView = adventureView.findViewById(R.id.view_adventure_last_paragraph)

        return AdventureView(
            adventureView,
            bookCover,
            bookName,
            lastPlayedAt,
            lastParagraph,
            adventureClickListener
        )
    }
}
