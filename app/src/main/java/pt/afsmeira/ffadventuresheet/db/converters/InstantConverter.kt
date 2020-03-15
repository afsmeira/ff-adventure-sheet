package pt.afsmeira.ffadventuresheet.db.converters

import androidx.room.TypeConverter
import java.time.Instant

/**
 * Convert between [Instant] and [Long] classes, for DB (de)serialization purposes.
 */
class InstantConverter {

    /**
     * Convert an Epoch millisecond timestamp to an [Instant].
     */
    @TypeConverter
    fun fromEpochTimestamp(timestamp: Long): Instant = Instant.ofEpochMilli(timestamp)

    /**
     * Convert an [Instant] to an Epoch millisecond timestamp.
     */
    @TypeConverter
    fun toEpochTimestamp(instant: Instant): Long = instant.toEpochMilli()
}
