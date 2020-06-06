package com.shareapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.shareapp.ShareApplication
import com.shareapp.di.component.ActivityComponent
import com.shareapp.di.component.DaggerActivityComponent
import com.shareapp.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VH : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VH

    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies(buildComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
//        viewModel.onCreate()
    }

    private fun buildComponent() =
        DaggerActivityComponent.builder()
            .applicationComponent((application as ShareApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build()


    protected open fun setupObservers() {

    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected abstract fun getDependencies(activityComponent: ActivityComponent)

    fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

}