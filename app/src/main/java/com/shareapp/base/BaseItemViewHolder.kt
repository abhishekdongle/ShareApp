package com.shareapp.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.shareapp.ShareApplication
import com.shareapp.di.component.DaggerViewHolderComponent
import com.shareapp.di.component.ViewHolderComponent
import com.shareapp.di.module.ViewHolderModule
import javax.inject.Inject

//
// Created by Abhishek.dongle on 01-Jun-20.
//
abstract class BaseItemViewHolder<T : Any, VM : BaseItemViewModel<T>>(
    @LayoutRes layoutId: Int, parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)),
    LifecycleOwner {

    @Inject
    lateinit var lifecycleRegistry: LifecycleRegistry

    @Inject
    lateinit var viewModel: VM

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    init {
        onCreate()
    }

    open fun bind(data: T) {
        viewModel.updateData(data)
    }

    fun onCreate() {
        getDependencies(buildViewHolderComponent())
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        setupView(itemView)
        setupObserver()
    }

    fun onStart() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    fun onStop() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    fun onDestroy() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    fun buildViewHolderComponent() = DaggerViewHolderComponent.builder()
        .applicationComponent((itemView.context.applicationContext as ShareApplication).applicationComponent)
        .viewHolderModule(ViewHolderModule(this)).build()

    protected open fun setupObserver() {

    }

    protected abstract fun getDependencies(viewHolderComponent: ViewHolderComponent)

    protected abstract fun setupView(itemView: View)
}