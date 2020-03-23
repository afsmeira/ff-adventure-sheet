package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
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
 * Changing the value of the underlying stat will trigger a call to [dataItemChangedListener].
 */
class TextStatView(
    self: android.view.View,
    private val name: TextView,
    private val value: EditText,
    dataItemChangedListener: DataItemChangedListener<Stat.Temporary>
) : DataAdapter.View<Stat.Temporary>(
    self,
    dataItemChangedListener = dataItemChangedListener
) {

    // Empty initialization, just to be able to call removeTextChangeListener, below
    private var debouncedTextChangedListener = DebouncedAfterTextChangedListener {}

    override fun bind(dataItem: Stat.Temporary) {
        // The text changed listener must be removed and recreated on every bind, to avoid having
        // a lot of listeners for the same view and to capture the bound data item
        value.removeTextChangedListener(debouncedTextChangedListener)
        debouncedTextChangedListener = DebouncedAfterTextChangedListener {
            dataItem.value = it
            dataItemChangedListener?.onDataItemChanged(dataItem, adapterPosition)
        }

        name.text = dataItem.stat.name
        value.setText(dataItem.value)
        value.hint = value.context.getString(R.string.view_stat_text_value_hint, dataItem.stat.name)

        value.addTextChangedListener(debouncedTextChangedListener)

        // Set the cursor to the end of the text, in case the bound view holder was the one the user
        // was using
        value.setSelection(dataItem.value.length)
    }

    companion object {

        /**
         * Factory method to create an [IntStatView] in the context of its [parent].
         */
        fun create(
            parent: ViewGroup,
            onDataChangedListener: DataItemChangedListener<Stat.Temporary>
        ): TextStatView {
            val textStatView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_text, parent, false)

            val name: TextView = textStatView.findViewById(R.id.view_stat_text_name)
            val value: EditText = textStatView.findViewById(R.id.view_stat_text_value)

            return TextStatView(textStatView, name, value, onDataChangedListener)
        }
    }
}
