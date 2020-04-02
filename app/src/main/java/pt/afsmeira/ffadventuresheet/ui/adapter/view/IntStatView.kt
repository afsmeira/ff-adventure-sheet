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
 * The view holder for a [Stat] that is represented by an integer value, consisting of a [TextView]
 * for the stat name and an [EditText] for the stat value.
 *
 * It is possible to set the value of the stat by using two [ImageButton] that increment and
 * decrement the stat value, or by using the [EditText] view directly.
 *
 * @param coroutineScope The scope where asynchronous data mutation occurs.
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
        value.setText(dataItem.typedValue.value.toString())
    }

    override fun onDataItemChanged(dataItem: Stat.Typed.Integer, newValue: Int) {
        dataItem.typedValue.value = newValue
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
