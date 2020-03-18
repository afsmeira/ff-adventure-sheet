package pt.afsmeira.ffadventuresheet.db.converters

import androidx.room.TypeConverter
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * Convert between a [Stat.Companion.Type] and a [String] for , for DB (de)serialization purposes.
 */
class StatTypeConverter {

    @TypeConverter
    fun fromString(value: String) = Stat.Companion.Type.valueOf(value)

    @TypeConverter
    fun toString(value: Stat.Companion.Type) = value.name
}
