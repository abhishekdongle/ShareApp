package com.shareapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shareapp.ShareApplication
import com.shareapp.data.local.db.DatabaseService
import com.shareapp.di.component.DaggerFragmentComponent
import com.shareapp.di.component.FragmentComponent
import com.shareapp.di.module.FragmentModule
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var databaseService: DatabaseService

    override
    fun onCreate(savedInstanceState: Bundle?) {
        getDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setupObserver()
//        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(provideLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun buildFragmentComponent() =
        DaggerFragmentComponent.builder()
            .applicationComponent((context!!.applicationContext as ShareApplication).applicationComponent)
            .fragmentModule(FragmentModule(this)).build()

    protected open fun setupObserver() {

    }

    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(view: View)

    protected abstract fun getDependencies(fragmentComponent: FragmentComponent)

    fun goBack() {
        if (activity is BaseActivity<*>) (activity as BaseActivity<*>).goBack()
    }
}