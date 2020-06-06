package com.shareapp.ui.home.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mindorks.bootcamp.instagram.utils.display.ScreenUtils
import com.shareapp.base.BaseItemViewModel
import com.shareapp.data.model.Post
import com.shareapp.utils.network.NetworkHelper
import javax.inject.Inject

//
// Created by Abhishek.dongle on 01-Jun-20.
//
class PostItemViewModel @Inject constructor(networkHelper: NetworkHelper) :
    BaseItemViewModel<Post>(networkHelper) {

    val name: LiveData<String> = Transformations.map(data) { it.name }
    val location: LiveData<String> = Transformations.map(data) { it.location }
    val caption: LiveData<String> = Transformations.map(data) { it.caption }
    val imgUrl: LiveData<String> = Transformations.map(data) { it.imageUrl }
    val isLiked: LiveData<Boolean> = Transformations.map(data) { it.isLiked }

    private val screenWidth = ScreenUtils.getScreenWidth()
    private val screenHeight = ScreenUtils.getScreenHeight()

}