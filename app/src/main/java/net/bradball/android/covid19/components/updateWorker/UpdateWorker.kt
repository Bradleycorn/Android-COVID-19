package net.bradball.android.covid19.components.updateWorker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import net.bradball.android.covid19.data.repos.CovidDataRepository
import net.bradball.android.covid19.network.GithubApi
import javax.inject.Inject

class UpdateWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {

    @Inject
    lateinit var covidRepo: CovidDataRepository

    override suspend fun doWork(): Result {


    }
}