package com.shareapp.ui.profile

import android.os.Bundle

import android.view.View
import com.shareapp.R
import com.shareapp.base.BaseFragment
import com.shareapp.data.remote.NetworkService
import com.shareapp.di.component.FragmentComponent
import javax.inject.Inject

class ProfileFragment : BaseFragment<ProfileViewModel>() {

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var networkService1: NetworkService

    companion object {
        var Tag: String = ProfileFragment::class.simpleName.toString()

        @JvmStatic
        fun newInstance(): ProfileFragment =
            ProfileFragment().apply {
                arguments = Bundle()
            }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_profile

    override fun setupView(view: View) {
    }

    override fun getDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }
}