package net.bradball.android.covid19.network

import retrofit2.Response
import retrofit2.http.GET

interface GithubApi {
    companion object {
        const val GITHUB_URL = "https://raw.githubusercontent.com/"
    }


    @GET("/nytimes/covid-19-data/master/us-counties.csv")
    suspend fun getCounties(): Response<String>
}