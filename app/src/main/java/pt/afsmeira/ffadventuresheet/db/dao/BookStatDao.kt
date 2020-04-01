package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Insert
import pt.afsmeira.ffadventuresheet.model.BookStat

@Dao
interface BookStatDao {

    @Insert
    suspend fun create(bookStats: List<BookStat>)
}
