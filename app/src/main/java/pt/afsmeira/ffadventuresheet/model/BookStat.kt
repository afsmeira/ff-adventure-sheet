package pt.afsmeira.ffadventuresheet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "book_stat",
    primaryKeys = ["book_id", "stat_id"],
    foreignKeys = [
        ForeignKey(entity = Book::class, parentColumns = ["id"], childColumns = ["book_id"]),
        ForeignKey(entity = Stat::class, parentColumns = ["id"], childColumns = ["stat_id"])
    ]
)
data class BookStat(
    @ColumnInfo(name = "book_id", index = true) val bookId: Long,
    @ColumnInfo(name = "stat_id") val statId: Long
)
