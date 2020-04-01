package net.bradball.android.covid19.di


import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import net.bradball.android.covid19.Covid19Application
import javax.inject.Singleton

/**
 * Dagger2 Component for allowing dynamic injection into our Activities, Fragments, ViewModels, etc.
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuildersModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Covid19Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: Covid19Application)
}
