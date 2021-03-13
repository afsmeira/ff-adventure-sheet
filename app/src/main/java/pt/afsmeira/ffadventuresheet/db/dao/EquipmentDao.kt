package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.model.Equipment

@Dao
interface EquipmentDao {

    @Insert
    suspend fun create(equipment: Equipment)

    @Delete
    suspend fun delete(equipment: Equipment)

    @Query("SELECT * FROM equipment WHERE adventure_id = :adventureId")
    suspend fun listForAdventure(adventureId: Long): List<Equipment>
}
