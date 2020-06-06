package com.shareapp

import android.app.Application
import com.shareapp.data.remote.NetworkService
import com.shareapp.di.component.ApplicationComponent
import com.shareapp.di.component.DaggerApplicationComponent
import com.shareapp.di.module.ApplicationModule
import javax.inject.Inject

class ShareApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    private var Tag: String = ShareApplication::class.simpleName.toString()

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var networkService1: NetworkService

    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }

    private fun getDependencies() {
        applicationComponent =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
    }
}