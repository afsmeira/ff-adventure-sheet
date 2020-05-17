package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import java.time.Instant

/**
 * The instantiation of a give [Stat] for a playthrough of a book, i.e. an [Adventure].
 *
 * `current_value` and `initial_value` are represented as strings and each [Stat.Type]
 * will decide on the format. This decision was made so that [Stat]s of different types could be
 * stored in the same table and be represented by a single [Entity], due to Room's limitations.
 *
 * @see Stat.Type for documentation on how each value is formatted to a string.
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
) : Updateable {

    /**
     * A generic [AdventureStat] and its [Stat.Typed] counterpart.
     */
    data class Typed<V: Stat.Value>(val adventureStat: AdventureStat, val typedStat: Stat.Typed<V>)
}

data class AdventureStatStat(
    @Embedded val adventureStat: AdventureStat,
    @Embedded val stat: Stat
) {

    /**
     * Convert a "generic" [AdventureStat] to its [AdventureStat.Typed] counterpart.
     */
    fun toTyped(): AdventureStat.Typed<*> =
        when (stat.type) {
            Stat.Type.INT ->
                AdventureStat.Typed(
                    adventureStat,
                    Stat.Typed.Integer(
                        stat.id,
                        stat.name,
                        Stat.Value.Single.Integer.fromJson(adventureStat.currentValue),
                        Stat.Value.Single.Integer.fromJson(adventureStat.initialValue)
                    )
                )
            Stat.Type.TEXT ->
                AdventureStat.Typed(
                    adventureStat,
                    Stat.Typed.Text(
                        stat.id,
                        stat.name,
                        Stat.Value.Single.Text.fromJson(adventureStat.currentValue),
                        Stat.Value.Single.Text.fromJson(adventureStat.initialValue)
                    )
                )
            Stat.Type.SINGLE_OPTION ->
                AdventureStat.Typed(
                    adventureStat,
                    Stat.Typed.SingleOption(
                        stat.id,
                        stat.name,
                        Stat.PossibleValues.fromJson(stat.possibleValues),
                        Stat.Value.Single.Text.fromJson(adventureStat.currentValue),
                        Stat.Value.Single.Text.fromJson(adventureStat.initialValue)
                    )
                )
            Stat.Type.MULTI_OPTION ->
                AdventureStat.Typed(
                    adventureStat,
                    Stat.Typed.MultiOption(
                        stat.id,
                        stat.name,
                        Stat.PossibleValues.fromJson(stat.possibleValues),
                        Stat.Value.Multiple.Option.Selectable.fromJson(adventureStat.currentValue),
                        Stat.Value.Multiple.Option.Selectable.fromJson(adventureStat.initialValue)
                    )
                )
            Stat.Type.MULTI_OPTION_REPEAT ->
                AdventureStat.Typed(
                    adventureStat,
                    Stat.Typed.MultiOptionRepeat(
                        stat.id,
                        stat.name,
                        Stat.PossibleValues.fromJson(stat.possibleValues),
                        Stat.Value.Multiple.Option.Repeatable.fromJson(adventureStat.currentValue),
                        Stat.Value.Multiple.Option.Repeatable.fromJson(adventureStat.initialValue)
                    )
                )
        }
}
