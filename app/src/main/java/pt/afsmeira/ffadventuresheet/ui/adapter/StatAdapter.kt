package pt.afsmeira.ffadventuresheet.ui.adapter

import android.view.ViewGroup
import android.widget.TextView
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.view.IntStatView
import pt.afsmeira.ffadventuresheet.ui.adapter.view.TextStatView

/**
 * [DataAdapter] for displaying an array of [Stat.Temporary].
 *
 * Since [Stat.Temporary] is mutable, when the underling data changes, that specific data item is
 * re-bound, via [notifyItemChanged].
 */
class StatAdapter(
    stats: Array<Stat.Temporary>
) : DataAdapter<Stat.Temporary>(stats) {

    private val onDataChangedListener =
        object : View.DataItemChangedListener<Stat.Temporary> {
            override fun onDataItemChanged(dataItem: Stat.Temporary, position: Int) {
                notifyItemChanged(position)
            }
    }

    // TODO Temporary code
    class StatView(private val self: TextView) : DataAdapter.View<Stat.Temporary>(self) {
        override fun bind(dataItem: Stat.Temporary) {
            self.text = dataItem.stat.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View<Stat.Temporary> =
        when (Stat.Type.valueOf(viewType)) {
            Stat.Type.INT              -> IntStatView.create(parent, onDataChangedListener)
            Stat.Type.TEXT             -> TextStatView.create(parent, onDataChangedListener)
            Stat.Type.SINGLE_OPT       -> StatView(TextView(parent.context))
            Stat.Type.MULTI_OPT        -> StatView(TextView(parent.context))
            Stat.Type.MULTI_OPT_REPEAT -> StatView(TextView(parent.context))
        }

    override fun getItemViewType(position: Int): Int = data[position].stat.type.ordinal
}
