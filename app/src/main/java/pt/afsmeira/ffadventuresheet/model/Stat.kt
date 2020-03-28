package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.afsmeira.ffadventuresheet.db.converters.StatPossibleValuesConverter
import pt.afsmeira.ffadventuresheet.db.InitialState
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
 * available will come from [InitialState].
 *
 * @see StatPossibleValuesConverter to understand how `possibleValues` is (de)serialized to/from the
 * DB.
 */
@Entity(tableName = "stat")
data class Stat(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: Type,
    @ColumnInfo(name = "possible_values") val possibleValues: PossibleValues = PossibleValues.Undefined
) {

    /**
     * Convert a "generic" [Stat] to its [Typed] counterpart.
     */
    fun toTyped(): Typed<*, *> =
        when(type) {
            Type.INT ->
                Typed.Integer(id, name)
            Type.TEXT ->
                Typed.Text(id, name)

            // The type casts can always fail, but we can guarantee that:
            // - object creation only occurs upon DB initialization.
            // - DB initialization data guarantees the cast succeeds.
            // See InitialStateTests.
            Type.SINGLE_OPTION ->
                Typed.SingleOption(id, name, possibleValues as PossibleValues.Defined)
            Type.MULTI_OPTION ->
                Typed.MultiOption(id, name, possibleValues as PossibleValues.Defined)
            Type.MULTI_OPTION_REPEAT ->
                Typed.MultiOptionRepeat(id, name, possibleValues as PossibleValues.Defined)
        }

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
        SINGLE_OPTION,

        /**
         * A stat that is represented by multiple unique values, chosen out of [possibleValues].
         */
        MULTI_OPTION,

        /**
         * A stat that is represented by multiple, and possibly repeated, values, chosen out of
         * [possibleValues].
         */
        MULTI_OPTION_REPEAT;

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
         * The value of a [Stat.Type.TEXT] or [Stat.Type.SINGLE_OPTION] stat.
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
         * The value of a [Stat.Type.MULTI_OPTION] stat.
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
         * The value of a [Stat.Type.MULTI_OPTION_REPEAT] stat.
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
                    .registerSubtype(MultiOption::class.java, MultiOption.typeLabel)
                    .registerSubtype(MultiOptionRepeat::class.java, MultiOptionRepeat.typeLabel)
        }
    }

    /**
     * A [Stat] that can exactly define it's type of [Value] and [PossibleValues].
     *
     * The `value` variable is the only new information when comparing to the [Stat] that originated
     * this class.
     *
     * @see (subclasses for more details)
     * @see Stat
     */
    sealed class Typed<P : PossibleValues, V : Value> {

        abstract val id: Long
        abstract val name: String
        abstract val type: Type
        abstract val possibleValues: P
        abstract var value: V

        // TODO Consider if the subclasses should be data classes or just regular classes

        /**
         * An integer stat.
         */
        data class Integer(
            override val id: Long,
            override val name: String,
            override var value: Value.Integer = Value.Integer.defaultValue
        ) : Typed<PossibleValues.Undefined, Value.Integer>() {
            override val type = Type.INT
            override val possibleValues = PossibleValues.Undefined
        }

        /**
         * A text stat.
         */
        data class Text(
            override val id: Long,
            override val name: String,
            override var value: Value.Text = Value.Text.defaultValue
        ) : Typed<PossibleValues.Undefined, Value.Text>() {
            override val type = Type.TEXT
            override val possibleValues = PossibleValues.Undefined
        }

        /**
         * A single option stat.
         */
        data class SingleOption(
            override val id: Long,
            override val name: String,
            override val possibleValues: PossibleValues.Defined,
            override var value: Value.Text = Value.Text.defaultValue
        ) : Typed<PossibleValues.Defined, Value.Text>() {
            override val type = Type.SINGLE_OPTION
        }

        /**
         * A multi option stat.
         */
        data class MultiOption(
            override val id: Long,
            override val name: String,
            override val possibleValues: PossibleValues.Defined,
            override var value: Value.MultiOption = Value.MultiOption.defaultValue
        ) : Typed<PossibleValues.Defined, Value.MultiOption>() {
            override val type = Type.MULTI_OPTION
        }

        /**
         * A repeatable multi option stat.
         */
        data class MultiOptionRepeat(
            override val id: Long,
            override val name: String,
            override val possibleValues: PossibleValues.Defined,
            override var value: Value.MultiOptionRepeat = Value.MultiOptionRepeat.defaultValue
        ) : Typed<PossibleValues.Defined, Value.MultiOptionRepeat>() {
            override val type = Type.MULTI_OPTION_REPEAT
        }
    }
}
