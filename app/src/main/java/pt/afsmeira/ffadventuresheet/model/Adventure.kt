package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

/**
 * A playthrough of a [Book].
 *
 * @param lastParagraph The paragraph where the adventure was halted.
 */
@Entity
data class Adventure(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "created_at") override val createdAt: Instant,
    @ColumnInfo(name = "updated_at") override val updatedAt: Instant,
    @ColumnInfo(name = "book_id") val bookId: Int,
    @ColumnInfo(name = "last_paragraph") val lastParagraph: Int
) : Updateable
