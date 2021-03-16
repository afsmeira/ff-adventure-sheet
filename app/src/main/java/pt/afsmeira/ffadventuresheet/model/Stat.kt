package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a unique feature of a [Book], that is available in the character sheet.
 *
 * Stats can be, and in some cases are, used in multiple books, but they are always unique within a
 * book.
 *
 * For example, `Skill`, `Stamina` and `Luck` are stats used in all books, but each never more than
 * once in a given book.
 */
@Entity(tableName = "stat")
data class Stat(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: Type,
    @ColumnInfo(name = "base_value") val baseValue: String? = null,
    val setup: Boolean = false
) {

    /**
     * Enum that represents the possible types of [Stat].
     */
    enum class Type {
        /**
         * A stat that is represented by an integer value.
         */
        INT,

        /**
         * A stat that is represented by a non-editable text value.
         */
        TEXT,

        BOOL,
        BUTTON,
        SPECIAL;

        companion object {

            /**
             * Returns the enum constant with the specified [ordinal].
             *
             * @throws IllegalArgumentException if this enum has no constant with the specified
             * ordinal.
             */
            fun valueOf(ordinal: Int): Type =
                values().find { it.ordinal == ordinal } ?:
                throw IllegalArgumentException(
                    "Type has no value with the specified ordinal ($ordinal)"
                )
        }
    }
}
