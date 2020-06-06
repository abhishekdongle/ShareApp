package com.shareapp.ui.addPhotos

import android.os.Bundle
import android.view.View

import com.shareapp.R
import com.shareapp.base.BaseFragment
import com.shareapp.di.component.FragmentComponent

class AddPhotosFragment : BaseFragment<AddPhotosViewModel>() {

    companion object {
        var Tag: String = AddPhotosFragment::class.simpleName.toString()

        @JvmStatic
        fun newInstance() =
            AddPhotosFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_add_photos

    override fun setupView(view: View) {
    }

    override fun getDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }
}
