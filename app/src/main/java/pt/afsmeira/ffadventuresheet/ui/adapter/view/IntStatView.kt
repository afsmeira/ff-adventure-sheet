package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter

/**
 * The view holder for a [Stat] that is represented by an integer value, consisting of two
 * [TextView]s, one for the stat name and the other for the stat value.
 *
 * It is possible to set the value of the stat by using two [ImageButton] that increment and
 * decrement the stat value.
 *
 * Changing the value of the underlying stat will trigger a call to [dataItemChangedListener].
 */
class IntStatView(
    self: android.view.View,
    private val name: TextView,
    private val value: EditText,
    private val add: ImageButton,
    private val subtract: ImageButton,
    dataItemChangedListener: DataItemChangedListener<Stat.Temporary>
) : DataAdapter.View<Stat.Temporary>(
    self,
    dataItemChangedListener = dataItemChangedListener
) {

    override fun bind(dataItem: Stat.Temporary) {
        name.text = dataItem.stat.name
        value.setText(dataItem.value)

        add.setOnClickListener {
            val intValue = value.text.toString().toInt()
            dataItem.value = (intValue + 1).toString()
            dataItemChangedListener?.onDataItemChanged(dataItem, adapterPosition)
        }
        subtract.setOnClickListener {
            val intValue = value.text.toString().toInt()
            if (intValue == 0) {
                return@setOnClickListener
            }
            dataItem.value = (intValue - 1).toString()
            dataItemChangedListener?.onDataItemChanged(dataItem, adapterPosition)
        }
    }

    companion object {

        /**
         * Factory method to create an [IntStatView] in the context of its [parent].
         */
        fun create(
            parent: ViewGroup,
            onDataChangedListener: DataItemChangedListener<Stat.Temporary>
        ): IntStatView {
            val intStatView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_int, parent, false)

            val name: TextView = intStatView.findViewById(R.id.view_stat_int_name)
            val value: EditText = intStatView.findViewById(R.id.view_stat_int_value)
            val add: ImageButton = intStatView.findViewById(R.id.view_stat_int_add_btn)
            val subtract: ImageButton = intStatView.findViewById(R.id.view_stat_int_subtract_btn)

            return IntStatView(intStatView, name, value, add, subtract, onDataChangedListener)
        }
    }
}
