package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * A [BaseIntView] for [Stat.Typed.Integer].
 *
 * @see BaseIntView
 */
class IntStatView(
    self: android.view.View,
    private val name: TextView,
    private val value: EditText,
    add: ImageButton,
    subtract: ImageButton,
    coroutineScope: CoroutineScope
) : BaseIntView<Stat.Typed.Integer>(self, name, value, add, subtract, coroutineScope) {

    override fun bind(dataItem: Stat.Typed.Integer) {
        super.bind(dataItem)

        // Set view values
        name.text = dataItem.name
        value.setText(dataItem.currentValue.value.toString())
    }

    override fun onDataItemChanged(dataItem: Stat.Typed.Integer, newValue: Int) {
        dataItem.currentValue.value = newValue
    }

    companion object {

        /**
         * Factory method to create an [IntStatView] in the context of its [parent].
         */
        fun create(parent: ViewGroup, coroutineScope: CoroutineScope): IntStatView {
            val intStatView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_int, parent, false)

            val name: TextView = intStatView.findViewById(R.id.view_stat_int_name)
            val value: EditText = intStatView.findViewById(R.id.view_stat_int_value)
            val add: ImageButton = intStatView.findViewById(R.id.view_stat_int_add_btn)
            val subtract: ImageButton = intStatView.findViewById(R.id.view_stat_int_subtract_btn)

            return IntStatView(intStatView, name, value, add, subtract, coroutineScope)
        }
    }
}
