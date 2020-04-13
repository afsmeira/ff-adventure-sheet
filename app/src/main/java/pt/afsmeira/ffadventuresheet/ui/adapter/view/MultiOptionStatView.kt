package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter
import java.lang.IllegalStateException

/**
 * The view holder for a [Stat.Typed] with [Stat.Value.Multiple] values, i.e. a stat that is
 * represented by multiple values.
 *
 * @param self The view representing the complete view holder.
 * @param name The text view for the stat name.
 * @param valuesRecycler The recycler view for the stat's possible values.
 * @param recycledViewPool The pool for views in the [valuesRecycler].
 * @param coroutineScope The lifecycle aware coroutine scope where asynchronous data mutation
 *        occurs.
 */
class MultiOptionStatView(
    private val self: android.view.View,
    private val name: TextView,
    private val valuesRecycler: RecyclerView,
    private val recycledViewPool: RecyclerView.RecycledViewPool,
    private val coroutineScope: CoroutineScope
) : DataAdapter.View<Stat.Typed<Stat.Value.Multiple<*>>>(self) {

    override fun bind(dataItem: Stat.Typed<Stat.Value.Multiple<*>>) {
        val layoutManager = LinearLayoutManager(self.context)
        layoutManager.initialPrefetchItemCount = dataItem.typedValue.values.size

        valuesRecycler.layoutManager = layoutManager
        valuesRecycler.adapter = MultiOptionStatAdapter(
            dataItem.typedValue.values,
            dataItem,
            coroutineScope
        )
        valuesRecycler.setRecycledViewPool(recycledViewPool)

        name.text = dataItem.name
    }

    companion object {

        /**
         * Factory method to create an [MultiOptionStatView] in the context of its [parent].
         */
        fun create(
            parent: ViewGroup,
            recycledViewPool: RecyclerView.RecycledViewPool,
            coroutineScope: CoroutineScope
        ): MultiOptionStatView {
            val multiOptionStatView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_multi_option, parent, false)

            val name: TextView = multiOptionStatView.findViewById(R.id.view_stat_multi_option_name)
            val valuesRecycler: RecyclerView =
                multiOptionStatView.findViewById(R.id.view_stat_multi_option_values_recycler)

            return MultiOptionStatView(
                multiOptionStatView,
                name,
                valuesRecycler,
                recycledViewPool,
                coroutineScope
            )
        }
    }

    /**
     * The [DataAdapter] with the possible values for the underlying [Stat.Typed].
     */
    private class MultiOptionStatAdapter(
        data: List<Stat.Value.Multiple.Option>,
        val parentStat: Stat.Typed<Stat.Value.Multiple<*>>,
        val coroutineScope: CoroutineScope
    ) : DataAdapter<Stat.Value.Multiple.Option>(data) {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): View<Stat.Value.Multiple.Option> {
            val statType = Stat.Type.valueOf(viewType)
            return when (statType) {
                Stat.Type.MULTI_OPTION ->
                    MultiOptionStatBooleanView.create(parent)
                Stat.Type.MULTI_OPTION_REPEAT ->
                    MultiOptionStatIntView.create(parent, coroutineScope)
                else ->
                    throw IllegalStateException("MultiOptionStatView not allowed for stats of type $statType")
            } as View<Stat.Value.Multiple.Option>
        }

        override fun getItemViewType(position: Int): Int = parentStat.type.ordinal
    }
}
