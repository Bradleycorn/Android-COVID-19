package net.bradball.android.covid19.di


import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides
import net.bradball.android.covid19.Covid19Application

@Module(includes = [ViewModelBuildersModule::class])
open class AppModule {

    @Provides
    open fun provideApplication(application: Covid19Application): Application = application

    @Provides
    open fun provideContext(application: Covid19Application): Context = application.applicationContext

}