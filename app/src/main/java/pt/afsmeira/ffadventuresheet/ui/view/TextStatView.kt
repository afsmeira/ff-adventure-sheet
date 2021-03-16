package pt.afsmeira.ffadventuresheet.ui.view

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.AdventureStat
import pt.afsmeira.ffadventuresheet.model.Stat

abstract class BaseTextStatView(
    context: Context,
    name: String,
    value: String
) : LinearLayout(context) {

    init {
        val view = inflate(context, R.layout.view_text_stat, this) // TODO How to fix this?
        view.findViewById<TextView>(R.id.view_text_stat_name).text = name
        view.findViewById<TextView>(R.id.view_text_stat_value).text = value
    }
}

class TextStatView(
    context: Context,
    private val stat: Stat,
    private val value: String
) : BaseTextStatView(context, stat.name, value), StatView {

    override fun getStat(): Stat = stat
    override fun getValue(): String = value
}

class TextAdventureStatView(
    context: Context,
    private val adventureStat: AdventureStat
) : BaseTextStatView(context, adventureStat.name, adventureStat.currentValue), AdventureStatView {

    override fun getAdventureStat(): AdventureStat = adventureStat
    override fun getValue(): String = adventureStat.currentValue
}
