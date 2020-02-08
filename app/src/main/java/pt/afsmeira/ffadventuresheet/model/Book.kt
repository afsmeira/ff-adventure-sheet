package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A Fighting Fantasy book.
 *
 * @param coverUrl The url for an image of the book cover.
 */
@Entity
data class Book(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "cover_url") val coverUrl: String
)
