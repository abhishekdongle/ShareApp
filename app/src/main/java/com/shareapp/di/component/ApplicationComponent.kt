package com.shareapp.di.component

import com.shareapp.ShareApplication
import com.shareapp.data.local.db.DatabaseService
import com.shareapp.data.remote.NetworkService
import com.shareapp.di.module.ApplicationModule
import com.shareapp.utils.network.NetworkHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: ShareApplication)

    fun getNetworkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper

    fun provideDatabaseService(): DatabaseService

}