package net.bradball.android.covid19

import android.app.Activity
import android.app.Application
import com.scichart.charting.visuals.SciChartSurface
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.bradball.android.covid19.di.DaggerApplicationComponent
import javax.inject.Inject

class Covid19Application: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        // Set this code once in MainActivity or application startup
        SciChartSurface.setRuntimeLicenseKey("EztYT4sQLcbnLK7oyE+b9JrIspuo9aGnDMLORlcZP3LHCsZ4zGYRX9bpK0kWhxylkQzZrg4x0gg73HIkr9yCtbcv82XPLVla9PoGlIyOxgJkxPJccNZz+omMjT9Aw/RF4Op/CHTlflms7lxvLwS67xoeErD+MmKbl2d/kX6QRzGYyKtQftvLRI4tqxwq72yLRVQQfdjaI3ZsoM2Bg9Haf0eRDYq6uGUwjhV7yrJNb6nCX0L0TBMXEitQ8eDfD3RRjpgX3xslK9aTOY9uEb6WgzdP0S9YXTdNjj2D2T0Iilq2YsKcbf1fkrJhvHfdX3lBCo+qsFZqn3+UNdhsHIj2rW2A5lLn1YHo52cUbzEDQKkpcYFAUCQ3ywnZygqq3k8OJnhnV8CIEoY+pPs3TyiyjPr+HUKAaXVsyDmfjcgco48c5GEe6Hss8gNJi+jZTjJvE70sknssOCO1Qdh+ItgwWQUoeMiQwEH1DPkxXXXq2g7uiTuLs8Z8N4OwVN6b+wfU8qOWM7XFcHXNni6xATXRQSYfKbVDJRNFP1uejDWG+JrxXdwVA3zrpoC79x+fg7mxRG62V/e2H5rEMeI=");

        setupDependencyInjection()
    }

    private fun setupDependencyInjection() {
        // Am I red? If so, you probably need to generate the DI code.
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}