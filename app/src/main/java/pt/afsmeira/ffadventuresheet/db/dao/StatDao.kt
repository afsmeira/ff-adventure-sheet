package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.db.InitialState
import pt.afsmeira.ffadventuresheet.model.AdventureStat
import pt.afsmeira.ffadventuresheet.model.AdventureStatStat
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * Data access object for [Stat].
 */
@Dao
interface StatDao {

    /**
     * Create a list of [Stat]s. This method should only be used to create the initial state for
     * [FFAdventureSheetDatabase].
     *
     * @see InitialState
     */
    @Insert
    suspend fun create(stats: List<Stat>)

    @Query("SELECT * FROM stat INNER JOIN book_stat ON id = stat_id WHERE book_id = :bookId")
    suspend fun listForBook(bookId: Long): List<Stat>

    @Insert
    suspend fun create(adventureStat: AdventureStat)

    @Query("SELECT * FROM adventure_stat JOIN stat ON stat.id = adventure_stat.stat_id WHERE adventure_stat.adventure_id = :adventureId")
    suspend fun listForAdventure(adventureId: Long): List<AdventureStatStat>
}
