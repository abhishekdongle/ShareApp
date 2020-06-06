package com.shareapp.di.module

import android.app.Application
import androidx.room.Room
import com.shareapp.ShareApplication
import com.shareapp.data.local.db.DatabaseService
import com.shareapp.data.remote.NetworkService
import com.shareapp.di.ApplicationContext
import com.shareapp.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ShareApplication) {

    @ApplicationContext
    @Provides
    fun provideApplicationContext(): Application = application

    @Singleton
    @Provides
    fun getNetworkService(): NetworkService {
        return NetworkService()
    }

    @Singleton
    @Provides
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(application, DatabaseService::class.java, "DatabaseService").build()

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}