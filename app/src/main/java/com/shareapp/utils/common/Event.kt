package com.shareapp.utils.common

import java.util.concurrent.atomic.AtomicBoolean

//
// Created by Abhishek.dongle on 31-May-20.
//
data class Event<out T>(private val content: T) {
    var hasBeenHandled = AtomicBoolean(false)

    fun getIsNotHandled(): T? = if (hasBeenHandled.getAndSet(true)) null else content

    fun peekValue(): T = content

}