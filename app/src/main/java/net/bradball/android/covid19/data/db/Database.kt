package net.bradball.android.covid19.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.bradball.android.covid19.data.db.converters.DateConverter
import net.bradball.android.covid19.data.models.CountyCases
import net.bradball.android.covid19.data.models.StateCases
import net.bradball.android.covid19.data.models.UsCases

@Database(
    entities = [
        CountyCases::class,
        StateCases::class,
        UsCases::class
    ],
    version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun casesDao(): CasesDao
}