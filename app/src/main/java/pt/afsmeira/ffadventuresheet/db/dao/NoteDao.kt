package pt.afsmeira.ffadventuresheet.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pt.afsmeira.ffadventuresheet.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun create(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note WHERE adventure_id = :adventureId")
    suspend fun listForAdventure(adventureId: Long): List<Note>
}
