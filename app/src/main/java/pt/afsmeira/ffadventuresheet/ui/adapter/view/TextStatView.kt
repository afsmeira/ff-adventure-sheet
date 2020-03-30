package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter
import pt.afsmeira.ffadventuresheet.util.DebouncedAfterTextChangedListener

/**
 * The view holder for a [Stat] that is represented by an free-text value, consisting of a
 * [TextView] for the stat name and an [EditText] for the stat value.
 *
 * It is possible to set the value of the stat using the [EditText] view directly.
 *
 * @param coroutineScope The scope where asynchronous data mutation occurs.
 */
class TextStatView(
    self: android.view.View,
    private val name: TextView,
    private val value: EditText,
    private val coroutineScope: CoroutineScope
) : DataAdapter.View<Stat.Typed.Text>(self) {

    // Empty initialization, just to be able to call removeTextChangeListener, below
    private var debouncedTextChangedListener = DebouncedAfterTextChangedListener {}

    override fun bind(dataItem: Stat.Typed.Text) {
        // The text changed listener must be removed and recreated on every bind, to avoid having
        // a lot of listeners for the same view and to capture the bound data item
        value.removeTextChangedListener(debouncedTextChangedListener)
        debouncedTextChangedListener =
            DebouncedAfterTextChangedListener(coroutineScope = coroutineScope) {
                dataItem.value.value = it
            }

        name.text = dataItem.name
        value.setText(dataItem.value.value)
        value.hint = value.context.getString(R.string.view_stat_text_value_hint, dataItem.name)

        value.addTextChangedListener(debouncedTextChangedListener)
    }

    companion object {

        /**
         * Factory method to create an [IntStatView] in the context of its [parent].
         */
        fun create(parent: ViewGroup, coroutineScope: CoroutineScope): TextStatView {
            val textStatView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_text, parent, false)

            val name: TextView = textStatView.findViewById(R.id.view_stat_text_name)
            val value: EditText = textStatView.findViewById(R.id.view_stat_text_value)

            return TextStatView(textStatView, name, value, coroutineScope)
        }
    }
}
