package pt.afsmeira.ffadventuresheet.util

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.ui.view.SingleOptionStatView
import pt.afsmeira.ffadventuresheet.ui.view.StatView
import pt.afsmeira.ffadventuresheet.ui.view.TextStatView
import java.lang.IllegalStateException

object StatProcessor {

    fun getValues(viewGroup: ViewGroup): List<StatView> =
        viewGroup.children
            .filter { v -> v is StatView }
            .map { v -> (v as StatView) }
            .toList()


    fun generateViews(context: Context, stats: List<Stat>): List<View> =
        stats.filter { s -> s.setup }.map { s ->
            when (s.type) {
                Stat.Type.INT -> processIntStat(context, s)
                Stat.Type.SPECIAL -> processSpecialStat(context, s)
                else -> throw IllegalStateException(String.format("Can't process %s stat '%s'", s.type, s.name))
            }
        }

    private fun processIntStat(context: Context, stat: Stat): TextStatView =
        if (stat.name == "Skill" || stat.name == "Luck") {
            TextStatView(context, stat, Dice.roll(1, 6, 6).toString())
        } else if (stat.name == "Stamina") {
            TextStatView(context, stat, Dice.roll(2, 6, 12).toString())
        } else {
            throw IllegalStateException(String.format("Can't process INT stat '%s'", stat.name))
        }

    private fun processSpecialStat(context: Context, stat: Stat): View =
        if (stat.name == "Potion") {
            SingleOptionStatView(context, stat, listOf("Skill", "Stamina", "Luck"))
        } else {
            throw IllegalStateException(String.format("Can't process SPECIAL stat '%s'", stat.name))
        }
}
