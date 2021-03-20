package pt.afsmeira.ffadventuresheet.ui.view

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Stat

class SingleOptionStatView(
    context: Context,
    private val stat: Stat,
    private val values: List<String>
) : LinearLayout(context), StatView {

    init {
        val view = inflate(context, R.layout.view_stat_single_option, this)
        view.findViewById<TextView>(R.id.view_stat_single_option_name).text = stat.name

        val spinnerAdapter = ArrayAdapter(
            context,
            R.layout.view_stat_single_option_option,
            values
        )
        view.findViewById<Spinner>(R.id.view_stat_single_option_values).apply {
            adapter = spinnerAdapter
            setSelection(0)
        }
    }

    override fun getStat(): Stat = stat
    override fun getValue(): String {
        val selectionIdx = findViewById<Spinner>(R.id.view_stat_single_option_values).selectedItemPosition
        return values[selectionIdx]
    }
}
