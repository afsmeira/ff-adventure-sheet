package pt.afsmeira.ffadventuresheet.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.view.*

/**
 * [DataAdapter] for displaying a list of [Stat.Typed].
 *
 * Since [Stat.Typed] is mutable, asynchronous data changes should occur inside [coroutineScope]
 * which should be the lifecycle aware scope of the activity where this adapter is used.
 */
class StatAdapter(
    stats: List<Stat.Typed<*>>,
    private val coroutineScope: CoroutineScope
) : DataAdapter<Stat.Typed<*>>(stats) {

    private val recycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View<Stat.Typed<*>> =
        when (Stat.Type.valueOf(viewType)) {
            Stat.Type.INT ->
                IntStatView.create(parent, coroutineScope)
            Stat.Type.TEXT ->
                TextStatView.create(parent, coroutineScope)
            Stat.Type.SINGLE_OPTION ->
                SingleOptionStatView.create(parent, coroutineScope)
            Stat.Type.MULTI_OPTION, Stat.Type.MULTI_OPTION_REPEAT ->
                MultiOptionStatView.create(parent, recycledViewPool, coroutineScope)
        } as View<Stat.Typed<*>>

    override fun getItemViewType(position: Int): Int = data[position].type.ordinal
}
