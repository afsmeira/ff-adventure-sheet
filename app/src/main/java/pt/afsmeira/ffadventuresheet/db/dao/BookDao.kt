package pt.afsmeira.ffadventuresheet.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * Data access object for [Book].
 */
@Dao
interface BookDao {

    @Insert
    suspend fun create(books: List<Book>)

    @Query("SELECT * FROM book")
    fun listAll(): LiveData<List<Book>>
}
