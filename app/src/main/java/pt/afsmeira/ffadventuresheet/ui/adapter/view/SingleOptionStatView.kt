package pt.afsmeira.ffadventuresheet.ui.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.adapter.DataAdapter

/**
 * The view holder for a [Stat] that is represented by a single value, selected among several
 * possible values, consisting of a [TextView] for the stat name and a [Spinner] for selecting the
 * stat value.
 *
 * @param coroutineScope The scope where asynchronous data mutation occurs.
 */
class SingleOptionStatView(
    self: android.view.View,
    private val name: TextView,
    private val values: Spinner,
    private val coroutineScope: CoroutineScope
) : DataAdapter.View<Stat.Typed.SingleOption>(self) {

    override fun bind(dataItem: Stat.Typed.SingleOption) {
        // Set possible values to choose from
        val valuesAdapter = ArrayAdapter(
            values.context,
            R.layout.view_stat_single_option_option,
            dataItem.possibleValues.values
        )
        values.adapter = valuesAdapter

        // Set view values
        val selectedIndex = dataItem.possibleValues.values.indexOf(dataItem.value.value)
        if (selectedIndex == -1) {
            values.setSelection(0)
        } else {
            values.setSelection(selectedIndex)
        }
        name.text = dataItem.name

        // Set selection listener
        values.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                coroutineScope.launch {
                    dataItem.value.value = dataItem.possibleValues.values[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    companion object {

        /**
         * Factory method to create an [IntStatView] in the context of its [parent].
         */
        fun create(parent: ViewGroup, coroutineScope: CoroutineScope): SingleOptionStatView {
            val singleOptionStatView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_stat_single_option, parent, false)

            val name: TextView =
                singleOptionStatView.findViewById(R.id.view_stat_single_option_name)
            val values: Spinner =
                singleOptionStatView.findViewById(R.id.view_stat_single_option_values)

            return SingleOptionStatView(singleOptionStatView, name, values, coroutineScope)
        }
    }
}
