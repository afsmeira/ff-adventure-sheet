package pt.afsmeira.ffadventuresheet.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * Convert between [Stat.Value] and [String], using a stringified JSON representation, for DB
 * (de)serialization purposes.
 */
class StatValueConverter {

    @TypeConverter
    fun fromString(value: String): Stat.Value = gson.fromJson(value, Stat.Value::class.java)

    @TypeConverter
    fun toString(value: Stat.Value): String = gson.toJson(value, Stat.Value::class.java)

    companion object {

        /**
         * The singleton [Gson] object that provides the conversion.
         */
        private val gson: Gson = GsonBuilder()
            .registerTypeAdapterFactory(Stat.Value.typeAdapterFactory)
            .create()
    }
}
