package pt.afsmeira.ffadventuresheet.model

/**
 * A playthrough of a [Book].
 *
 * @param lastParagraph The paragraph where the adventure was halted.
 */
data class Adventure(val id: Int, val bookId: Int, val lastParagraph: Int)
