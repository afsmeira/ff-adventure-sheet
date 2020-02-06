package pt.afsmeira.ffadventuresheet.model

/**
 * A Fighting Fantasy book.
 *
 * @param coverUrl The url for an image of the book cover.
 */
data class Book(val id: Long, val name: String, val coverUrl: String)
