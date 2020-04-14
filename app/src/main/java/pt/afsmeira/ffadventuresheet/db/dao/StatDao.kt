package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.db.InitialState
import pt.afsmeira.ffadventuresheet.model.*

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

    /**
     * Create a list of [BookStat]s. This method should only be used to create the initial state for
     * [FFAdventureSheetDatabase].
     *
     * @see InitialState
     */
    @Insert
    suspend fun createBookStats(bookStats: List<BookStat>)

    /**
     * Get all stats for the [Book] identified by [bookId].
     */
    @Query("SELECT * FROM stat INNER JOIN book_stat ON id = stat_id WHERE book_id = :bookId")
    suspend fun listForBook(bookId: Long): List<Stat>

    /**
     * Get all stats from the [Adventure] identified by [adventureId].
     */
    @Query("SELECT * FROM adventure_stat JOIN stat ON stat.id = adventure_stat.stat_id WHERE adventure_stat.adventure_id = :adventureId")
    suspend fun listForAdventure(adventureId: Long): List<AdventureStatStat>
}
