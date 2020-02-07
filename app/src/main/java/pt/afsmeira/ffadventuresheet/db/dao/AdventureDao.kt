package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.model.Adventure

@Dao
interface AdventureDao {
    @Query("SELECT * FROM adventure")
    fun listAll(): Array<Adventure>

    @Insert
    fun create(adventure: Adventure)
}
