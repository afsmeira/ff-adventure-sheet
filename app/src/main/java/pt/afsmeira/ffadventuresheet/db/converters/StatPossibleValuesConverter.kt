package pt.afsmeira.ffadventuresheet.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * Convert between [Stat.PossibleValues] and [String], using a stringified JSON representation, for
 * DB (de)serialization purposes.
 */
class StatPossibleValuesConverter {

    @TypeConverter
    fun fromString(value: String): Stat.PossibleValues =
        gson.fromJson(value, Stat.PossibleValues::class.java)

    @TypeConverter
    fun toString(value: Stat.PossibleValues): String =
        gson.toJson(value, Stat.PossibleValues::class.java)

    companion object {

        /**
         * The singleton [Gson] object that provides the conversion.
         */
        private val gson: Gson = GsonBuilder()
            .registerTypeAdapterFactory(Stat.PossibleValues.typeAdapterFactory)
            .create()
    }
}
