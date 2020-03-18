package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * Data access object for [Stat].
 */
@Dao
interface StatDao {

    @Query("SELECT * FROM stat INNER JOIN book_stat ON id = stat_id WHERE book_id = :bookId")
    suspend fun listForBook(bookId: Long): Array<Stat>
}
