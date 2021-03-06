package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pt.afsmeira.ffadventuresheet.db.InitialState

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
 * `null`. Although, this is not strictly enforced, the only [Stat] instances available will come
 * from [InitialState].
 */
@Entity(tableName = "stat")
data class Stat(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: Type,
    @ColumnInfo(name = "possible_values") val possibleValues: String? = null
) {

    /**
     * Convert a "generic" [Stat] to its [Typed] counterpart.
     */
    fun toTyped(): Typed<*> =
        when (type) {
            Type.INT ->
                Typed.Integer(id, name)
            Type.TEXT ->
                Typed.Text(id, name)
            Type.SINGLE_OPTION ->
                Typed.SingleOption(id, name, PossibleValues.fromJson(possibleValues))
            Type.MULTI_OPTION ->
                Typed.MultiOption(id, name, PossibleValues.fromJson(possibleValues))
            Type.MULTI_OPTION_REPEAT ->
                Typed.MultiOptionRepeat(id, name, PossibleValues.fromJson(possibleValues))
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
     * The finite set of possible values of a [Stat], represented by a list of strings.
     */
    object PossibleValues {
        fun fromJson(json: String?) : List<String> =
            gson.fromJson(json, object: TypeToken<List<String>>(){}.type)

        fun toJson(possibleValues: List<String>): String =
            gson.toJson(possibleValues, object: TypeToken<List<String>>(){}.type)
    }

    /**
     * The actual value of a [Stat].
     */
    sealed class Value {

        /**
         * JSON representation of the underlying [Value] class.
         */
        fun toJson(): String = gson.toJson(this)

        /**
         * A stat that is represented by a single value.
         */
        sealed class Single<T> : Value() {

            /**
             * The value of the stat.
             */
            abstract val value: T

            /**
             * The value of a [Stat.Type.INT] stat.
             */
            data class Integer(override var value: Int) : Single<Int>() {

                companion object {
                    fun defaultValue() = Integer(0)

                    /**
                     * Generate an [Integer] instance from its stringified JSON representation.
                     */
                    fun fromJson(value: String): Integer = gson.fromJson(value, Integer::class.java)
                }
            }

            /**
             * The value of a [Stat.Type.TEXT] or [Stat.Type.SINGLE_OPTION] stat.
             */
            data class Text(override var value: String?) : Single<String?>() {

                companion object {
                    fun defaultValue() = Text(null)

                    /**
                     * Generate a [Text] instance from its stringified JSON representation.
                     */
                    fun fromJson(value: String): Text = gson.fromJson(value, Text::class.java)
                }
            }
        }

        /**
         * The value of a [Stat.Type.MULTI_OPTION] or [Stat.Type.MULTI_OPTION_REPEAT] stat.
         */
        data class Multiple<T : Multiple.Option>(val values: List<T>) : Value() {

            /**
             * An option for the value of this stat.
             */
            sealed class Option {

                /**
                 * The option name.
                 */
                abstract val name: String

                /**
                 * An option and whether it was selected.
                 */
                data class Selectable(
                    override val name: String,
                    var selected: Boolean = false
                ) : Option() {

                    companion object {

                        /**
                         * Generate a [Multiple] instance, with [Selectable] options from its
                         * stringified JSON representation.
                         */
                        fun fromJson(value: String): Multiple<Selectable> {
                            val listType = object : TypeToken<Multiple<Selectable>>() {}.type
                            return gson.fromJson(value, listType)
                        }
                    }
                }

                /**
                 * An option and the number of times it was selected.
                 */
                data class Repeatable(
                    override val name: String,
                    var repetitions: Int = 0
                ) : Option() {

                    companion object {

                        /**
                         * Generate a [Multiple] instance, with [Repeatable] options from its
                         * stringified JSON representation.
                         */
                        fun fromJson(value: String): Multiple<Repeatable> {
                            val listType = object : TypeToken<Multiple<Repeatable>>() {}.type
                            return gson.fromJson(value, listType)
                        }
                    }
                }
            }
        }
    }

    /**
     * A [Stat] or [AdventureStat] that can exactly define its type of [Value].
     *
     * Typed stats that are created from [Stat] will have [initialValue] undefined. Although
     * [currentValue] is not mutable, its data structure allows for mutability.
     *
     * @see (subclasses for more details)
     * @see Stat
     * @see Value
     */
    sealed class Typed<V : Value> {

        abstract val id: Long
        abstract val name: String
        abstract val type: Type
        abstract val possibleValues: List<String>?
        abstract val currentValue: V
        abstract val initialValue: V?

        /**
         * An integer stat.
         */
        data class Integer(
            override val id: Long,
            override val name: String,
            override val currentValue: Value.Single.Integer = Value.Single.Integer.defaultValue(),
            override val initialValue: Value.Single.Integer? = null
        ) : Typed<Value.Single.Integer>() {

            override val type = Type.INT
            override val possibleValues: List<String>? = null
        }

        /**
         * A text stat.
         */
        data class Text(
            override val id: Long,
            override val name: String,
            override val currentValue: Value.Single.Text = Value.Single.Text.defaultValue(),
            override val initialValue: Value.Single.Text? = null
        ) : Typed<Value.Single.Text>() {

            override val type = Type.TEXT
            override val possibleValues: List<String>? = null
        }

        /**
         * A single option stat.
         */
        data class SingleOption(
            override val id: Long,
            override val name: String,
            override val possibleValues: List<String>,
            override val currentValue: Value.Single.Text = Value.Single.Text.defaultValue(),
            override val initialValue: Value.Single.Text? = null
        ) : Typed<Value.Single.Text>() {

            override val type = Type.SINGLE_OPTION
        }

        /**
         * A multi option stat.
         */
        data class MultiOption(
            override val id: Long,
            override val name: String,
            override val possibleValues: List<String>,
            override val currentValue: Value.Multiple<Value.Multiple.Option.Selectable> =
                Value.Multiple(possibleValues.map { Value.Multiple.Option.Selectable(it) }),
            override val initialValue: Value.Multiple<Value.Multiple.Option.Selectable>? = null
        ) : Typed<Value.Multiple<Value.Multiple.Option.Selectable>>() {

            override val type = Type.MULTI_OPTION
        }

        /**
         * A repeatable multi option stat.
         */
        data class MultiOptionRepeat(
            override val id: Long,
            override val name: String,
            override val possibleValues: List<String>,
            override val currentValue: Value.Multiple<Value.Multiple.Option.Repeatable> =
                Value.Multiple(possibleValues.map { Value.Multiple.Option.Repeatable(it) }),
            override val initialValue: Value.Multiple<Value.Multiple.Option.Repeatable>? = null
        ) : Typed<Value.Multiple<Value.Multiple.Option.Repeatable>>() {

            override val type = Type.MULTI_OPTION_REPEAT
        }
    }

    companion object {

        /**
         * Gson object for [Stat], and associated classes, (de)serialization.
         */
        private val gson = Gson()
    }
}
