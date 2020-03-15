package pt.afsmeira.ffadventuresheet.db.dao

import androidx.annotation.VisibleForTesting
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

    @Query("SELECT * FROM book")
    fun listAll(): LiveData<Array<Book>>

    // Room does not allow an in-memory database to be created from a file and it's not advisable to
    // use a real database because it might introduce flakiness (rows might are already exist).
    // Furthermore, creating the books using a "manual" query was not working since our read queries
    // use LiveData and the inserts were done outside of the DAO scope and they were not visible for
    // the LiveData objects.
    // Therefore, this method exists only to be used in tests.
    @VisibleForTesting
    @Insert
    suspend fun create(book: Book)
}
