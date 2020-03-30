package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat

class MultiOptionStatIntView(
    self: android.view.View,
    private val name: TextView,
    private val value: EditText,
    add: ImageButton,
    subtract: ImageButton,
    coroutineScope: CoroutineScope
) : BaseIntView<Stat.Value.MultiOptionRepeat.Option>(self, name, value, add, subtract, coroutineScope) {


    override fun bind(dataItem: Stat.Value.MultiOptionRepeat.Option) {
        super.bind(dataItem)

        // Set view values
        name.text = dataItem.value
        value.setText(dataItem.repetitions.toString())
    }

    override fun onDataItemChanged(dataItem: Stat.Value.MultiOptionRepeat.Option, newValue: Int) {
        dataItem.repetitions = newValue
    }

    companion object {

        /**
         * Factory method to create an [MultiOptionStatIntView] in the context of its [parent].
         */
        fun create(parent: ViewGroup, coroutineScope: CoroutineScope): MultiOptionStatIntView {
            val intStatView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_int, parent, false)

            val name: TextView = intStatView.findViewById(R.id.view_stat_int_name)
            val value: EditText = intStatView.findViewById(R.id.view_stat_int_value)
            val add: ImageButton = intStatView.findViewById(R.id.view_stat_int_add_btn)
            val subtract: ImageButton = intStatView.findViewById(R.id.view_stat_int_subtract_btn)

            return MultiOptionStatIntView(intStatView, name, value, add, subtract, coroutineScope)
        }
    }
}
