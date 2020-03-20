package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import java.time.Instant

/**
 * The instantiation of a give [Stat] for a playthrough of a book, i.e. an [Adventure].
 *
 * `current_value` and `initial_value` are represented as strings and each [Stat.Companion.Type]
 * will decide on the format. This decision was made so that [Stat]s of different types could be
 * stored in the same table and be represented by a single [Entity], due to Room's limitations.
 *
 * @see Stat.Companion.Type for documentation on how each value is formatted to a string.
 */
@Entity(
    tableName = "adventure_stat",
    primaryKeys = ["adventure_id", "stat_id"],
    foreignKeys = [
        ForeignKey(
            entity = Adventure::class,
            parentColumns = ["id"],
            childColumns = ["adventure_id"]
        ),
        ForeignKey(
            entity = Stat::class,
            parentColumns = ["id"],
            childColumns = ["stat_id"]
        )
    ]
)
data class AdventureStat(
    @ColumnInfo(name = "adventure_id", index = true) val adventureId: Long,
    @ColumnInfo(name = "stat_id") val statId: Long,
    @ColumnInfo(name = "created_at") override val createdAt: Instant,
    @ColumnInfo(name = "updated_at") override val updatedAt: Instant,
    @ColumnInfo(name = "current_value") val currentValue: String,
    @ColumnInfo(name = "initial_value") val initialValue: String
) : Updateable

data class AdventureStatStat(
    @Embedded val adventureStat: AdventureStat,
    @Embedded val stat: Stat
)
