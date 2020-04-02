package pt.afsmeira.ffadventuresheet.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.db.FFAdventureSheetDatabase
import pt.afsmeira.ffadventuresheet.db.InitialState
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * Data access object for [Book].
 */
@Dao
interface BookDao {

    /**
     * Create a list of [Book]s. This method should only be used to create the initial state for
     * [FFAdventureSheetDatabase].
     *
     * @see InitialState
     */
    @Insert
    suspend fun create(books: List<Book>)

    @Query("SELECT * FROM book")
    fun listAll(): LiveData<List<Book>>
}
