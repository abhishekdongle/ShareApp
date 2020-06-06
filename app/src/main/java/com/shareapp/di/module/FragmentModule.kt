package com.shareapp.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shareapp.di.ActivityContext
import com.shareapp.ui.addPhotos.AddPhotosViewModel
import com.shareapp.ui.home.HomeViewModel
import com.shareapp.ui.home.post.PostAdapter
import com.shareapp.ui.profile.ProfileViewModel
import com.shareapp.utils.ViewModelProviderFactory
import com.shareapp.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = fragment.context!!

    @Provides
    fun provideProfileViewModel(
        networkHelper: NetworkHelper
    ): ProfileViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(ProfileViewModel::class) {
            ProfileViewModel(networkHelper)
        }).get(ProfileViewModel::class.java)

    @Provides
    fun provideHomeViewModel(
        networkHelper: NetworkHelper
    ): HomeViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(
                networkHelper, ArrayList())
        }).get(HomeViewModel::class.java)

    @Provides
    fun providePhotoViewModel(
        networkHelper: NetworkHelper
    ): AddPhotosViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(AddPhotosViewModel::class) {
            AddPhotosViewModel(
                networkHelper
            )
        }).get(AddPhotosViewModel::class.java)

    @Provides
    fun providePostsAdapter() = PostAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

}