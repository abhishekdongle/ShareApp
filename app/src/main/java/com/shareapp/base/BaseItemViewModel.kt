package com.shareapp.base

import androidx.lifecycle.MutableLiveData
import com.shareapp.utils.network.NetworkHelper

//
// Created by Abhishek.dongle on 01-Jun-20.
//
abstract class BaseItemViewModel<T : Any>(val networkHelper: NetworkHelper) :
    BaseViewModel(networkHelper) {
    val data: MutableLiveData<T> = MutableLiveData()

    fun updateData(data: T) {
        this.data.postValue(data)
    }
}