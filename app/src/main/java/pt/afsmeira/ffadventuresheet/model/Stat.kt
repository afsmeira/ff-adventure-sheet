package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.afsmeira.ffadventuresheet.db.converters.StringArrayConverter

/**
 * Represents a unique feature of a [Book], that is available in the character sheet.
 *
 * Stats can be, and in some cases are, used in multiple books, but they are always unique within a
 * book.
 *
 * For example, `Skill`, `Stamina` and `Luck` are stats used in all books, but never each more than
 * once in a given book.
 *
 * Note that if the stat is of [Type.INT] or [Type.TEXT], `possible_values` should be empty.
 * Although, this is not strictly enforced, the only [Stat] instances available will come from the
 * initial DB (creation of stats is not possible), which will move the "enforcement" point to the
 * DB initialization script.
 *
 * @see StringArrayConverter to understand how `possibleValues` is (de)serialized to/from the DB.
 */
@Entity(tableName = "stat")
data class Stat(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val type: Type,
    @ColumnInfo(name = "possible_values") val possibleValues: Array<String>
) {
    companion object {

        /**
         * Enum that represents the possible types of [Stat].
         */
        enum class Type {
            /**
             * A stat that is represented by an integer value.
             */
            INT,

            /**
             * A stat that is represented by a free-text value.
             */
            TEXT,

            /**
             * A stat that is represented by a single value, chosen out of [possibleValues].
             */
            SINGLE_OPT,

            /**
             * A stat that is represented by multiple unique values, chosen out of [possibleValues].
             */
            MULTI_OPT,

            /**
             * A stat that is represented by multiple, and possibly repeated, values, chosen out of
             * [possibleValues].
             */
            MULTI_OPT_REPEAT
        }
    }
}
