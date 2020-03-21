package pt.afsmeira.ffadventuresheet.ui.adapter

import android.view.ViewGroup
import android.widget.TextView
import pt.afsmeira.ffadventuresheet.model.Stat

// TODO Temporary code
class StatAdapter(
    stats: Array<Stat>,
    statClickListener: ClickListener<Stat>
) : DataAdapter<Stat>(stats, statClickListener) {

    class StatView(private val self: TextView) : DataAdapter.View<Stat>(self) {
        override fun bind(dataItem: Stat) {
            self.text = dataItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View<Stat> {
        val label: TextView = TextView(parent.context)
        return StatView(label)
    }
}
