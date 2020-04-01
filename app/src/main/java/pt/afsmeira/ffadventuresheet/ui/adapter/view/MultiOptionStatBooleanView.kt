package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ToggleButton
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter

class MultiOptionStatBooleanView(
    self: android.view.View,
    private val name: ToggleButton
) : DataAdapter.View<Stat.Value.MultiOption.Option>(self) {

    override fun bind(dataItem: Stat.Value.MultiOption.Option) {
        name.text = dataItem.name
        name.textOn = dataItem.name
        name.textOff = dataItem.name

        name.setOnCheckedChangeListener { _, checked ->
            dataItem.selected = checked
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ): MultiOptionStatBooleanView {
            val multiOptionStatBooleanView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_multi_option_boolean_option, parent, false)

            val toggleButton: ToggleButton =
                multiOptionStatBooleanView.findViewById(R.id.view_stat_multi_option_boolean_option)

            return MultiOptionStatBooleanView(
                multiOptionStatBooleanView,
                toggleButton
            )
        }
    }
}
