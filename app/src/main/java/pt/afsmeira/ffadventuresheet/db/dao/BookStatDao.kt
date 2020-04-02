package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Insert
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.db.InitialState
import pt.afsmeira.ffadventuresheet.model.BookStat

/**
 * Data access object for [BookStat].
 */
@Dao
interface BookStatDao {

    /**
     * Create a list of [BookStat]s. This method should only be used to create the initial state for
     * [FFAdventureSheetDatabase].
     *
     * @see InitialState
     */
    @Insert
    suspend fun create(bookStats: List<BookStat>)
}
