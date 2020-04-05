package net.bradball.android.covid19.data.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.bradball.android.covid19.data.models.CountyCases
import net.bradball.android.covid19.data.models.StateCases
import net.bradball.android.covid19.data.models.UsCases

@Dao
interface CasesDao {

    @Query("SELECT * FROM CountyCases WHERE county = :county ORDER BY date ASC")
    abstract fun getCountyCases(county: String): Flow<List<CountyCases>>

    @Query("SELECT * FROM StateCases ORDER BY date ASC")
    abstract fun getStateCases(): Flow<List<StateCases>>

    @Query("SELECT * FROM UsCases ORDER BY date ASC")
    abstract fun getUsCases(): Flow<List<UsCases>>
}