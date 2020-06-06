package com.shareapp.di.component

import com.shareapp.di.ViewModelScope
import com.shareapp.di.module.ViewHolderModule
import com.shareapp.ui.home.post.PostItemViewHolder
import dagger.Component

//
// Created by Abhishek.dongle on 01-Jun-20.
//
@ViewModelScope
@Component(modules = [ViewHolderModule::class], dependencies = [ApplicationComponent::class])
interface ViewHolderComponent {
    fun inject(viewHolder: PostItemViewHolder)

}