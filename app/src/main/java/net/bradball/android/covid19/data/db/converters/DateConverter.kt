package net.bradball.android.covid19.data.db.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    companion object {

        @JvmStatic
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        @JvmStatic
        @TypeConverter
        fun toTimestamp(date: Date?): Long? {
            return date?.time
        }

    }

}