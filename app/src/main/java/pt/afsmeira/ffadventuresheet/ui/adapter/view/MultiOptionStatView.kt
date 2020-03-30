package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter

class MultiOptionStatView(
    private val self: android.view.View,
    private val name: TextView,
    private val valuesRecycler: RecyclerView,
    private val recycledViewPool: RecyclerView.RecycledViewPool
) : DataAdapter.View<Stat.Typed.MultiOption>(self) {

    override fun bind(dataItem: Stat.Typed.MultiOption) {
        val layoutManager = LinearLayoutManager(self.context)
        layoutManager.initialPrefetchItemCount = dataItem.value.values.size

        valuesRecycler.layoutManager = layoutManager
        valuesRecycler.adapter = MultiOptionStatAdapter(dataItem.value.values.toTypedArray())
        valuesRecycler.setRecycledViewPool(recycledViewPool)

        name.text = dataItem.name
    }

    companion object {
        fun create(
            parent: ViewGroup,
            recycledViewPool: RecyclerView.RecycledViewPool
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
                recycledViewPool
            )
        }
    }

    private class MultiOptionStatAdapter(
        data: Array<Stat.Value.MultiOption.Option>
    ) : DataAdapter<Stat.Value.MultiOption.Option>(data) {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): View<Stat.Value.MultiOption.Option> =
            MultiOptionStatBooleanView.create(parent)
    }
}
