package com.shareapp.di.component

import com.shareapp.di.ActivityScope
import com.shareapp.di.module.ActivityModule
import com.shareapp.ui.main.MainActivity
import com.shareapp.ui.login.LoginActivity
import com.shareapp.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: SplashActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)

}