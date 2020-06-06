package com.shareapp.data.model

//
// Created by Abhishek.dongle on 01-Jun-20.
//
data class Post(
    var id: Long,
    var name: String,
    var location: String,
    var caption: String,
    var imageUrl: String,
    var isLiked: Boolean
)