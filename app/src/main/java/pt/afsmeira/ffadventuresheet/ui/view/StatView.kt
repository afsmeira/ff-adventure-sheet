package pt.afsmeira.ffadventuresheet.ui.view

import pt.afsmeira.ffadventuresheet.model.AdventureStat
import pt.afsmeira.ffadventuresheet.model.Stat

interface StatView {

    fun getStat(): Stat
    fun getValue(): String
}

interface AdventureStatView {

    fun getAdventureStat(): AdventureStat
    fun getValue(): String
}
