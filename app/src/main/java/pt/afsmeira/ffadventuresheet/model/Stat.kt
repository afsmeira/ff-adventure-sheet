package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.afsmeira.ffadventuresheet.db.converters.StatPossibleValuesConverter
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
 * Note that if the stat is of [Type.INT] or [Type.TEXT], `possible_values` should be
 * [PossibleValues.Undefined]. Although, this is not strictly enforced, the only [Stat] instances
 * available will come from the initial DB (creation of stats is not possible), which will move the
 * "enforcement" point to the DB initialization script.
 *
 * @see StatPossibleValuesConverter to understand how `possibleValues` is (de)serialized to/from the
 * DB.
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
        MULTI_OPT_REPEAT;

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

    data class Temporary(
        val stat: Stat,
        var value: String = ""
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
             * The type adapter factory that defines how [PossibleValues] and its subclasses are
             * (de)serialized to JSON.
             */
            val typeAdapterFactory: RuntimeTypeAdapterFactory<PossibleValues> =
                RuntimeTypeAdapterFactory
                    .of(PossibleValues::class.java, typeFieldName)
                    .registerSubtype(Undefined::class.java, Undefined.typeLabel)
                    .registerSubtype(Defined::class.java, Defined.typeLabel)
        }
    }

    /**
     * The actual value of a [Stat].
     */
    sealed class Value {

        /**
         * The value of a [Stat.Type.INT] stat.
         */
        data class Integer(val value: Int) : Value() {

            companion object {

                /**
                 * The value for the `type` field of the JSON representation for this class.
                 */
                const val typeLabel = "integer"

                val defaultValue = Integer(0)
            }
        }

        /**
         * The value of a [Stat.Type.TEXT] stat.
         */
        data class Text(val value: String?) : Value() {

            companion object {

                /**
                 * The value for the `type` field of the JSON representation for this class.
                 */
                const val typeLabel = "text"

                val defaultValue = Text(null)
            }
        }

        /**
         * The value of a [Stat.Type.SINGLE_OPT] stat.
         */
        data class SingleOption(val value: String?) : Value() {

            companion object {

                /**
                 * The value for the `type` field of the JSON representation for this class.
                 */
                const val typeLabel = "single_option"

                val defaultValue = SingleOption(null)
            }
        }

        /**
         * The value of a [Stat.Type.MULTI_OPT] stat.
         */
        data class MultiOption(val values: Set<String>) : Value() {

            companion object {

                /**
                 * The value for the `type` field of the JSON representation for this class.
                 */
                const val typeLabel = "multi_option"

                val defaultValue = MultiOption(emptySet())
            }
        }

        /**
         * The value of a [Stat.Type.MULTI_OPT_REPEAT] stat.
         */
        data class MultiOptionRepeat(val values: Set<Option>) : Value() {

            /**
             * The selected option and the number of times it was selected.
             */
            data class Option(val value: String, val repetitions: Int)

            companion object {

                /**
                 * The value for the `type` field of the JSON representation for this class.
                 */
                const val typeLabel = "multi_option_repeat"

                val defaultValue = MultiOptionRepeat(emptySet())
            }
        }

        companion object {

            /**
             * The name of the field that represents the class type, in the JSON representation for
             * this class.
             */
            private const val typeFieldName = "type"

            /**
             * The type adapter factory that defines how [Value] and its subclasses are
             * (de)serialized to JSON.
             */
            val typeAdapterFactory: RuntimeTypeAdapterFactory<Value> =
                RuntimeTypeAdapterFactory
                    .of(Value::class.java, typeFieldName)
                    .registerSubtype(Integer::class.java, Integer.typeLabel)
                    .registerSubtype(Text::class.java, Text.typeLabel)
                    .registerSubtype(SingleOption::class.java, SingleOption.typeLabel)
                    .registerSubtype(MultiOption::class.java, MultiOption.typeLabel)
                    .registerSubtype(MultiOptionRepeat::class.java, MultiOptionRepeat.typeLabel)
        }
    }

    /**
     * A [Stat] that has the correct types of [PossibleValues]. It should define a variable field,
     * `value`, that has the correct type of [Value].
     *
     * The `value` field is the only new information when comparing to the [Stat] that originated
     * this class.
     *
     * @see (subclasses for more details)
     */
    sealed class Typed(id: Long, name: String, type: Type) {

        // TODO Consider if the subclasses should be data classes or just regular classes

        /**
         * An integer stat.
         */
        data class Integer(
            val id: Long,
            val name: String,
            var value: Value.Integer = Value.Integer.defaultValue
        ) : Typed(id, name, Type.INT) {
            val possibleValues = PossibleValues.Undefined
        }

        /**
         * A text stat.
         */
        data class Text(
            val id: Long,
            val name: String,
            var value: Value.Text = Value.Text.defaultValue
        ) : Typed(id, name, Type.TEXT) {
            val possibleValues = PossibleValues.Undefined
        }

        /**
         * A single option stat.
         */
        data class SingleOption(
            val id: Long,
            val name: String,
            val possibleValues: PossibleValues.Defined,
            var value: Value.SingleOption = Value.SingleOption.defaultValue
        ) : Typed(id, name, Type.SINGLE_OPT)

        /**
         * A multi option stat.
         */
        data class MultiOption(
            val id: Long,
            val name: String,
            val possibleValues: PossibleValues.Defined,
            var value: Value.MultiOption = Value.MultiOption.defaultValue
        ) : Typed(id, name, Type.MULTI_OPT)

        /**
         * A repeatable multi option stat.
         */
        data class MultiOptionRepeat(
            val id: Long,
            val name: String,
            val possibleValues: PossibleValues.Defined,
            var value: Value.MultiOptionRepeat = Value.MultiOptionRepeat.defaultValue
        ) : Typed(id, name, Type.MULTI_OPT_REPEAT)
    }
}
