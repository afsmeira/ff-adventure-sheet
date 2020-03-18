package pt.afsmeira.ffadventuresheet.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Convert between [Array]`<String>` and [String], using a stringified JSON array representation,
 * for DB (de)serialization purposes.
 */
class StringArrayConverter {

    @TypeConverter
    fun fromJsonArray(value: String): Array<String> =
        Gson().fromJson(value, TypeToken.getArray(String::class.java).type)

    @TypeConverter
    fun toJsonArray(values: Array<String>): String = Gson().toJson(values)
}
