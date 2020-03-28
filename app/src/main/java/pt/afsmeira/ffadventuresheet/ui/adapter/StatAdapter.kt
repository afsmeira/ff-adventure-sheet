package pt.afsmeira.ffadventuresheet.ui.adapter

import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.view.IntStatView
import pt.afsmeira.ffadventuresheet.ui.adapter.view.SingleOptionStatView
import pt.afsmeira.ffadventuresheet.ui.adapter.view.TextStatView

/**
 * [DataAdapter] for displaying an array of [Stat.Typed].
 *
 * Since [Stat.Typed] is mutable, asynchronous data changes should occur inside [coroutineScope]
 * which should be the life-cycle aware scope of the activity where this adapter is used.
 */
class StatAdapter(
    stats: Array<Stat.Typed<*, *>>,
    private val coroutineScope: CoroutineScope
) : DataAdapter<Stat.Typed<*, *>>(stats) {

    // TODO Temporary code
    class StatView(private val self: TextView) : DataAdapter.View<Stat.Typed<*, *>>(self) {
        override fun bind(dataItem: Stat.Typed<*, *>) {
            self.text = ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View<Stat.Typed<*, *>> =
        when (Stat.Type.valueOf(viewType)) {
            Stat.Type.INT              -> IntStatView.create(parent, coroutineScope) as View<Stat.Typed<*, *>>
            Stat.Type.TEXT             -> TextStatView.create(parent, coroutineScope) as View<Stat.Typed<*, *>>
            Stat.Type.SINGLE_OPT       -> SingleOptionStatView.create(parent, coroutineScope) as View<Stat.Typed<*, *>>
            Stat.Type.MULTI_OPT        -> StatView(TextView(parent.context))
            Stat.Type.MULTI_OPT_REPEAT -> StatView(TextView(parent.context))
        }

    override fun getItemViewType(position: Int): Int = data[position].type.ordinal
}
