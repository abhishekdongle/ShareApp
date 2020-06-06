package com.shareapp.di.module

import androidx.lifecycle.ViewModelProviders
import com.shareapp.base.BaseActivity
import com.shareapp.ui.main.MainViewModel
import com.shareapp.ui.login.LoginViewModel
import com.shareapp.ui.splash.SplashViewModel
import com.shareapp.utils.ViewModelProviderFactory
import com.shareapp.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideSplashViewModel(networkHelper: NetworkHelper): SplashViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(networkHelper)
            //this lambda creates and return SplashViewModel
        }).get(SplashViewModel::class.java)

    @Provides
    fun provideLoginViewModel(networkHelper: NetworkHelper): LoginViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(LoginViewModel::class) {
            LoginViewModel(networkHelper)
        }).get(LoginViewModel::class.java)

    @Provides
    fun provideHomeViewModel(networkHelper: NetworkHelper): MainViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(networkHelper)
        }).get(MainViewModel::class.java)

}