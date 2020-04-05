package net.bradball.android.covid19.data.repos

import android.util.Log
import com.opencsv.CSVReader
import com.opencsv.processor.RowProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import net.bradball.android.covid19.data.db.AppDatabase
import net.bradball.android.covid19.data.models.County
import net.bradball.android.covid19.data.models.CountyCases
import net.bradball.android.covid19.data.models.StateCases
import net.bradball.android.covid19.data.models.UsCases
import net.bradball.android.covid19.network.GithubApi
import java.io.StringReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CovidDataRepository @Inject constructor(private val githubApi: GithubApi,
                                              private val db: AppDatabase) {

    suspend fun fetchCountyData() = withContext(Dispatchers.IO) {
        val res = githubApi.getCounties()
        val csv = res.body()


        val usData = mutableListOf<UsCases>()
        val stateData = mutableListOf<StateCases>()
        val countyData = mutableListOf<County>()


        com.opencsv.CSVReaderBuilder(StringReader(csv))
            .withSkipLines(1)
            .build().readAll()

        Log.d("RETROFIT_DEBUG", "RESPONSE: ${res.body() ?: "failed"}")
    }


    fun getCases(county: String): Flow<List<CountyCases>> = db.casesDao().getCountyCases(county)

}