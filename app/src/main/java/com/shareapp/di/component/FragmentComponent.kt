package com.shareapp.di.component

import com.shareapp.di.FragmentScope
import com.shareapp.di.module.FragmentModule
import com.shareapp.ui.addPhotos.AddPhotosFragment
import com.shareapp.ui.home.HomeFragment
import com.shareapp.ui.profile.ProfileFragment
import dagger.Component

@FragmentScope
@Component(modules = [FragmentModule::class], dependencies = [ApplicationComponent::class])
interface FragmentComponent {
    fun inject(fragment: ProfileFragment)

    fun inject(fragment: HomeFragment)

    fun inject(fragment: AddPhotosFragment)
}