package com.shareapp.ui.home.post

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.shareapp.base.BaseAdapter
import com.shareapp.data.model.Post

//
// Created by Abhishek.dongle on 01-Jun-20.
//
class PostAdapter(parentLifecycle: Lifecycle, posts: ArrayList<Post>) :
    BaseAdapter<Post, PostItemViewHolder>(parentLifecycle, posts) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostItemViewHolder(parent)

}