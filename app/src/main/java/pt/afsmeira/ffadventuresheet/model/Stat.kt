package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.afsmeira.ffadventuresheet.db.converters.StringArrayConverter
import pt.afsmeira.ffadventuresheet.util.RuntimeTypeAdapterFactory
import java.lang.IllegalArgumentException

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
    /**
     * Enum that represents the possible types of [Stat].
     *
     * @param defaultValue The default value for this stat. It must have a String representation
     * provided by `toString`.
     */
    enum class Type(val defaultValue: Any) {
        /**
         * A stat that is represented by an integer value.
         */
        INT(defaultValue = 0),

        /**
         * A stat that is represented by a free-text value.
         */
        TEXT(defaultValue = ""),

        /**
         * A stat that is represented by a single value, chosen out of [possibleValues].
         */
        SINGLE_OPT(defaultValue = ""),

        /**
         * A stat that is represented by multiple unique values, chosen out of [possibleValues].
         */
        MULTI_OPT(defaultValue = ""),

        /**
         * A stat that is represented by multiple, and possibly repeated, values, chosen out of
         * [possibleValues].
         */
        MULTI_OPT_REPEAT(defaultValue = "");

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

    /**
     * A data class for a stat and its temporary value. [value] has a default value matching the
     * default value of the stat type.
     */
    data class Temporary(
        val stat: Stat,
        var value: String = stat.type.defaultValue.toString()
    )

    /**
     * The finite set of possible values of a [Stat].
     */
    sealed class PossibleValues {

        /**
         * Represents the lack of defined possible values, either because it's a [Stat] with a free
         * text field, or because it's an integer field.
         */
        object Undefined : PossibleValues() {

            /**
             * The value for the `type` field of the JSON representation for this class.
             */
            const val typeLabel = "undefined"
        }

        /**
         * Represents the set of defined possible values for a [Stat].
         */
        data class Defined(val values: List<String>) : PossibleValues() {

            companion object {

                /**
                 * The value for the `type` field of the JSON representation for this class.
                 */
                const val typeLabel = "defined"
            }
        }

        companion object {

            /**
             * The name of the field that represents the class type, in the JSON representation for
             * this class.
             */
            private const val typeFieldName = "type"

            /**
             * The type adapter factory that defines of [PossibleValues] and its subclasses are
             * (de)serialized to JSON.
             */
            val typeAdapterFactory: RuntimeTypeAdapterFactory<PossibleValues> =
                RuntimeTypeAdapterFactory
                    .of(PossibleValues::class.java, typeFieldName)
                    .registerSubtype(Undefined::class.java, Undefined.typeLabel)
                    .registerSubtype(Defined::class.java, Defined.typeLabel)
        }
    }
}
