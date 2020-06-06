package com.shareapp.ui.home.post

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.shareapp.R
import com.shareapp.base.BaseItemViewHolder
import com.shareapp.data.model.Post
import com.shareapp.di.component.ViewHolderComponent
import com.shareapp.utils.common.GlideRequestOption
import com.shareapp.utils.common.GlideRequestOption.getRequestOption
import kotlinx.android.synthetic.main.item_view_post.view.*


//
// Created by Abhishek.dongle on 01-Jun-20.
//
class PostItemViewHolder(parent: ViewGroup) : BaseItemViewHolder<Post, PostItemViewModel>(
    R.layout.item_view_post, parent
) {

    override fun getDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(itemView: View) {
        var name: String? = null

        viewModel.name.observe(this, Observer {
            itemView.tvName.text = it
            name = it
        })

        viewModel.location.observe(this, Observer {
            itemView.tvLocation.text = it
        })

        viewModel.imgUrl.observe(this, Observer {
            it.run {
                Glide.with(itemView.ivPost.context)
                    .load(it).apply(getRequestOption()).into(itemView.ivPost)
            }
        })

        viewModel.caption.observe(this, Observer {
            itemView.tvCaption.text = name + " " + it
        })

        viewModel.isLiked.observe(this, Observer {
            if (it) itemView.ivLike.setImageResource(R.drawable.ic_heart_selected)
            else itemView.ivLike.setImageResource(R.drawable.ic_heart_unselected)
        })
    }
}