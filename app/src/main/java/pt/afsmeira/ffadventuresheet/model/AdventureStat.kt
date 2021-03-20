package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import pt.afsmeira.ffadventuresheet.ui.view.StatView
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
            childColumns = ["adventure_id"],
            onDelete = CASCADE
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
) : Updateable {

    companion object AdventureStat {
        fun create(
            bookId: Long,
            setupStats: List<StatView>,
            nonSetupStats: List<Stat>
        ): List<pt.afsmeira.ffadventuresheet.model.AdventureStat> {

            return when(bookId) {
                1L -> createTheWizardOfFiretopMountain(setupStats, nonSetupStats)
                else -> listOf()
            }
        }

        private fun createTheWizardOfFiretopMountain(
            setupStats: List<StatView>,
            nonSetupStats: List<Stat>
        ): List<pt.afsmeira.ffadventuresheet.model.AdventureStat> {
            val now = Instant.now()

            return setupStats.map { s ->
                val adventureStat = AdventureStat(
                    adventureId = 0, // This will be replaced before persisting
                    name = s.getStat().name,
                    type = s.getStat().type,
                    currentValue = s.getValue(),
                    initialValue = s.getValue(),
                    createdAt = now,
                    updatedAt = now
                )
                if (s.getStat().name == "Potion") {
                    adventureStat.copy(
                        name = s.getValue() + " Potion",
                        type = Stat.Type.BUTTON,
                        currentValue = s.getStat().baseValue ?: "",
                        initialValue = s.getStat().baseValue
                    )
                } else {
                    adventureStat
                }
            } + nonSetupStats.map { s ->
                AdventureStat(
                    adventureId = 0, // This will be replaced before persisting
                    name = s.name,
                    type = s.type,
                    currentValue = s.baseValue ?: "",
                    initialValue = s.baseValue,
                    createdAt = now,
                    updatedAt = now
                )
            }
        }
    }
}
