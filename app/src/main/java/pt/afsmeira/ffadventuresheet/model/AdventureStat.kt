package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.Instant

/**
 * The instantiation of a give [Stat] for a playthrough of a book, i.e. an [Adventure].
 *
 * `current_value` and `initial_value` are represented as strings and each [Stat.Type]
 * will decide on the format. This decision was made so that [Stat]s of different types could be
 * stored in the same table and be represented by a single [Entity], due to Room's limitations.
 */
@Entity(
    tableName = "adventure_stat",
    foreignKeys = [
        ForeignKey(
            entity = Adventure::class,
            parentColumns = ["id"],
            childColumns = ["adventure_id"]
        )
    ]
)
data class AdventureStat(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "adventure_id", index = true) val adventureId: Long,
    val name: String,
    val type: Stat.Type,
    @ColumnInfo(name = "current_value") val currentValue: String,
    @ColumnInfo(name = "initial_value") val initialValue: String?,
    @ColumnInfo(name = "created_at") override val createdAt: Instant,
    @ColumnInfo(name = "updated_at") override val updatedAt: Instant
) : Updateable
