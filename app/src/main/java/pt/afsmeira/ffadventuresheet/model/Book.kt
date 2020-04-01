package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A Fighting Fantasy book.
 *
 * @param coverUrl The url for an image of the book cover.
 */
@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "cover_url") val coverUrl: String
)
