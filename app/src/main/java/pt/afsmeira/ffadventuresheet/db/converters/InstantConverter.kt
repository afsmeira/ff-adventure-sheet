package pt.afsmeira.ffadventuresheet.db.converters

import androidx.room.TypeConverter
import java.time.Instant

/**
 * Convert between [Instant] and [Long] classes, for DB (de)serialization purposes.
 */
class InstantConverter {

    @TypeConverter
    fun fromEpochTimestamp(timestamp: Long): Instant = Instant.ofEpochMilli(timestamp)

    @TypeConverter
    fun toEpochTimestamp(instant: Instant): Long = instant.toEpochMilli()
}
