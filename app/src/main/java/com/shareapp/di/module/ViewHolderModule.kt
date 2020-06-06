package com.shareapp.di.module

import androidx.lifecycle.LifecycleRegistry
import com.shareapp.base.BaseItemViewHolder
import com.shareapp.di.ViewModelScope
import dagger.Module
import dagger.Provides

//
// Created by Abhishek.dongle on 01-Jun-20.
//
@Module
class ViewHolderModule(private val baseItemViewHolder: BaseItemViewHolder<*, *>) {

    @ViewModelScope
    @Provides
    fun lifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(baseItemViewHolder)
}