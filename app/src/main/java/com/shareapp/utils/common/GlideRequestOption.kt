package com.shareapp.utils.common

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.shareapp.R

//
// Created by Abhishek.dongle on 02-Jun-20.
//
object GlideRequestOption {

    fun getRequestOption(): RequestOptions {
        return RequestOptions()
            .centerCrop()
            .placeholder(R.color.grey)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.mipmap.ic_launcher_round)
    }
}