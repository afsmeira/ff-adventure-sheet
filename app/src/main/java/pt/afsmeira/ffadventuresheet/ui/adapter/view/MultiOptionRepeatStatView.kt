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

class MultiOptionRepeatStatView(
    private val self: android.view.View,
    private val name: TextView,
    private val valuesRecycler: RecyclerView,
    private val recycledViewPool: RecyclerView.RecycledViewPool,
    private val coroutineScope: CoroutineScope
) : DataAdapter.View<Stat.Typed.MultiOptionRepeat>(self) {

    override fun bind(dataItem: Stat.Typed.MultiOptionRepeat) {
        val layoutManager = LinearLayoutManager(self.context)
        layoutManager.initialPrefetchItemCount = dataItem.value.values.size

        valuesRecycler.layoutManager = layoutManager
        valuesRecycler.adapter =
            MultiOptionRepeatStatAdapter(dataItem.value.values.toTypedArray(), coroutineScope)
        valuesRecycler.setRecycledViewPool(recycledViewPool)

        name.text = dataItem.name
    }

    companion object {
        fun create(
            parent: ViewGroup,
            recycledViewPool: RecyclerView.RecycledViewPool,
            coroutineScope: CoroutineScope
        ): MultiOptionRepeatStatView {
            val multiOptionStatView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_multi_option, parent, false)

            val name: TextView = multiOptionStatView.findViewById(R.id.view_stat_multi_option_name)
            val valuesRecycler: RecyclerView =
                multiOptionStatView.findViewById(R.id.view_stat_multi_option_values_recycler)

            return MultiOptionRepeatStatView(
                multiOptionStatView,
                name,
                valuesRecycler,
                recycledViewPool,
                coroutineScope
            )
        }
    }

    private class MultiOptionRepeatStatAdapter(
        data: Array<Stat.Value.MultiOptionRepeat.Option>,
        private val coroutineScope: CoroutineScope
    ) : DataAdapter<Stat.Value.MultiOptionRepeat.Option>(data) {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): View<Stat.Value.MultiOptionRepeat.Option> =
            MultiOptionStatIntView.create(parent, coroutineScope)
    }
}
