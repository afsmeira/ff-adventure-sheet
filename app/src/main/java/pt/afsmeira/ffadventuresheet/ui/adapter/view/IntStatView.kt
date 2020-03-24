package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter
import pt.afsmeira.ffadventuresheet.util.DebouncedAfterTextChangedListener

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
    private val add: ImageButton,
    private val subtract: ImageButton,
    private val coroutineScope: CoroutineScope
) : DataAdapter.View<Stat.Temporary>(self) {

    // Empty initialization, just to be able to call removeTextChangeListener, below
    private var debouncedTextChangedListener = DebouncedAfterTextChangedListener {}

    override fun bind(dataItem: Stat.Temporary) {
        // The text changed listener must be removed and recreated on every bind, to avoid having
        // a lot of listeners for the same view and to capture the bound data item
        value.removeTextChangedListener(debouncedTextChangedListener)
        debouncedTextChangedListener =
            DebouncedAfterTextChangedListener(coroutineScope = coroutineScope) {
                dataItem.value = it
            }

        // Set view values
        name.text = dataItem.stat.name
        value.setText(dataItem.value)

        // Add or set listeners
        // Setting listeners replaces existing ones
        add.setOnClickListener {
            coroutineScope.launch {
                val currentValue = value.text.toString().toInt()
                val newValue = currentValue + 1

                dataItem.value = newValue.toString()
                value.setText(newValue.toString())
            }
        }
        subtract.setOnClickListener {
            coroutineScope.launch {
                val currentValue = value.text.toString().toInt()
                val newValue = currentValue - 1

                if (newValue >= 0) {
                    dataItem.value = newValue.toString()
                    value.setText(newValue.toString())
                }
            }
        }
        value.addTextChangedListener(debouncedTextChangedListener)
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
