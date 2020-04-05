package net.bradball.android.covid19.di


import android.app.Application
import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import net.bradball.android.covid19.Covid19Application
import net.bradball.android.covid19.data.db.AppDatabase
import net.bradball.android.covid19.network.GithubApi
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelBuildersModule::class])
open class AppModule {

    @Provides
    open fun provideApplication(application: Covid19Application): Application = application

    @Provides
    open fun provideContext(application: Covid19Application): Context = application.applicationContext

    @Provides
    @Singleton
    open fun provideGithubApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(GithubApi.GITHUB_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    open fun provideAppDatabase(app: Application): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, "AppDB")
        /**
         * Add Migration paths between schema versions when needed (i.e. addMigrations(AppDatabase.MIGRATION_1_2,AppDatabase.MIGRATION_2_3)).
         * We don't have a need to provide any migration scripts in between versions as of now since we don't need to preserve any data
         */
        .addMigrations()
        /**
         *  This allow room to destructively recreate the database tables when there is schema upgrade and no migration path are provided.
         *  Note: you must increase the version property in AppDatabase when making database changes in order for the upgrade to work without crashing the app.
         */
        .fallbackToDestructiveMigration() // This allow room to destructively recreate the database tables when there is schema upgrade and no migration path are provided
        .build()

}