package pt.afsmeira.ffadventuresheet.db.converters

import androidx.room.TypeConverter
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * Convert between a [Stat.Type] and a [String] for DB (de)serialization purposes.
 */
class StatTypeConverter {

    @TypeConverter
    fun fromString(value: String) = Stat.Type.valueOf(value)

    @TypeConverter
    fun toString(value: Stat.Type) = value.name
}
