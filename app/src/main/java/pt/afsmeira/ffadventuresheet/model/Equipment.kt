package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "equipment",
    foreignKeys = [
        ForeignKey(
            entity = Adventure::class,
            parentColumns = ["id"],
            childColumns = ["adventure_id"]
        )
    ]
)
data class Equipment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "adventure_id", index = true) val adventureId: Long,
    val value: String,
    @ColumnInfo(name = "created_at") override val createdAt: Instant
) : Creatable
