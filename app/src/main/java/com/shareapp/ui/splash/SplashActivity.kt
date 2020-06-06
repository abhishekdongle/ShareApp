package com.shareapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.shareapp.R
import com.shareapp.base.BaseActivity
import com.shareapp.data.remote.NetworkService
import com.shareapp.di.component.ActivityComponent
import com.shareapp.ui.login.LoginActivity

import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel>() {
    private var Tag: String = SplashActivity::class.simpleName.toString()
    val work: String? = null
    private var test: String = "Splash"


    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var networkService1: NetworkService

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun setupView(savedInstanceState: Bundle?) {
        var l = test.let { it.toUpperCase() }

        test.apply { test.length }

        Log.d(Tag, "test " + test)
        Log.d(Tag, "l " + l)
        Log.d(Tag, "" + work)

        Thread.sleep(10000)
//        Handler().postDelayed(Runnable {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
//        }, 3000)
    }

    override fun getDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}
