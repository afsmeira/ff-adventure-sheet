package pt.afsmeira.ffadventuresheet.model

import androidx.room.Embedded

/**
 * A data class that relates an [Adventure] and its corresponding [Book].
 */
data class AdventureBook(
    @Embedded(prefix = "a_") val adventure: Adventure,
    @Embedded(prefix = "b_") val book: Book
)