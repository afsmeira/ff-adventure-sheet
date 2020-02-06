package pt.afsmeira.ffadventuresheet.model

/**
 * Represents a unique feature of a [Book], that is available in the character sheet.
 *
 * Stats can be, and in some cases are, used in multiple books, but they are always unique within a
 * book.
 *
 * For example, `Skill`, `Stamina` and `Luck` are stats used in all books, but never each more than
 * once in a given book.
 */
sealed class Stat {
    abstract val id: Long
    abstract val name: String
}

/**
 * A stat that is represented by an integer value.
 */
data class IntStat(override val id: Long, override val name: String) : Stat()

/**
 * A stat that is represented by a free-text value.
 */
data class TextStat(override val id: Long, override val name: String): Stat()

/**
 * A stat that is represented by a single value, chosen out of [possibleValues].
 */
data class SingleOptionStat(
    override val id: Long,
    override val name: String,
    val possibleValues: Set<String>
): Stat()

/**
 * A stat that is represented by multiple unique values, chosen out of [possibleValues].
 */
data class MultiOptionStat(
    override val id: Long,
    override val name: String,
    val possibleValues: Set<String>
): Stat()

/**
 * A stat that is represented by multiple, and possibly repeated, values, chosen out of
 * [possibleValues].
 */
data class MultiOptionRepeatStat(
    override val id: Long,
    override val name: String,
    val possibleValues: Set<String>
): Stat()
