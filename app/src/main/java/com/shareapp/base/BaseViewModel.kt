package com.shareapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shareapp.R
import com.shareapp.utils.common.Resource
import com.shareapp.utils.network.NetworkHelper

abstract class BaseViewModel(private val networkHelper: NetworkHelper) : ViewModel() {

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage() {
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
        }
    }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

//    abstract fun onCreate()

}