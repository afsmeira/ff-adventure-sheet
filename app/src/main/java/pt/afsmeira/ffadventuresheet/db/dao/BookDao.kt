package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * Data access object for [Book].
 */
@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun listAll(): Array<Book>
}
