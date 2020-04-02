package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter
import pt.afsmeira.ffadventuresheet.util.DebouncedAfterTextChangedListener

/**
 * The view holder for a [Stat] that is represented by an integer value.
 *
 * @param self The view representing the complete view holder.
 * @param name The text view for the stat name.
 * @param value The edit text view for setting the stat value.
 * @param add A button to increment the stat value.
 * @param subtract A button to decrement the stat value.
 * @param coroutineScope The lifecycle aware coroutine scope where asynchronous data mutation
 *        occurs.
 * @param T The underlying stat.
 */
abstract class BaseIntView<T>(
    self: android.view.View,
    private val name: TextView,
    private val value: EditText,
    private val add: ImageButton,
    private val subtract: ImageButton,
    private val coroutineScope: CoroutineScope
) : DataAdapter.View<T>(self) {

    // Empty initialization, just to be able to call removeTextChangeListener, below
    private var debouncedTextChangedListener = DebouncedAfterTextChangedListener {}

    override fun bind(dataItem: T) {
        // The text changed listener must be removed and recreated on every bind, to avoid having
        // a lot of listeners for the same view and to capture the bound data item
        value.removeTextChangedListener(debouncedTextChangedListener)
        debouncedTextChangedListener =
            DebouncedAfterTextChangedListener(coroutineScope = coroutineScope) {
                onDataItemChanged(dataItem, it.toInt())
            }

        // Add or set listeners
        // Setting listeners replaces existing ones
        add.setOnClickListener {
            coroutineScope.launch {
                val currentValue = value.text.toString().toInt()
                val newValue = currentValue + 1

                onDataItemChanged(dataItem, newValue)
                value.setText(newValue.toString())
            }
        }
        subtract.setOnClickListener {
            coroutineScope.launch {
                val currentValue = value.text.toString().toInt()
                val newValue = currentValue - 1

                if (newValue >= 0) {
                    onDataItemChanged(dataItem, newValue)
                    value.setText(newValue.toString())
                }
            }
        }
        value.addTextChangedListener(debouncedTextChangedListener)
    }

    /**
     * Method called when the integer value for the stat changes, through the UI controls.
     *
     * @param dataItem The underlying typed stat.
     * @param newValue The new value for typed stat.
     */
    abstract fun onDataItemChanged(dataItem: T, newValue: Int)
}
