package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.Instant

/**
 * A playthrough of a [Book].
 *
 * @param lastParagraph The paragraph where the adventure was halted.
 */
@Entity(
    tableName = "adventure",
    foreignKeys = [
        ForeignKey(entity = Book::class, parentColumns = ["id"], childColumns = ["book_id"])
    ]
)
data class Adventure(
    // TODO Consider typing Ids - https://github.com/afsmeira/ff-adventure-sheet/issues/34
    // We don't want to set ids when creating a new adventure (or object in general), so the id is
    // set to 0, since SQLite will generate a new id, when id = 0 is being persisted and
    // `autoGenerate` is set to true.
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "created_at") override val createdAt: Instant,
    @ColumnInfo(name = "updated_at") override val updatedAt: Instant,
    @ColumnInfo(name = "book_id", index = true) val bookId: Long,
    @ColumnInfo(name = "last_paragraph") val lastParagraph: Int = FIRST_PARAGRAPH
) : Updateable {

    companion object {
        const val FIRST_PARAGRAPH = 1
    }
}
