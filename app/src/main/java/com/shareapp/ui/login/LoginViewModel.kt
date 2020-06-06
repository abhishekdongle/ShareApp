package com.shareapp.ui.login

import androidx.lifecycle.MutableLiveData
import com.shareapp.R
import com.shareapp.base.BaseViewModel
import com.shareapp.utils.common.Resource
import com.shareapp.utils.network.NetworkHelper

class LoginViewModel(private val networkHelper: NetworkHelper) : BaseViewModel(networkHelper) {
    val loggingIn = MutableLiveData<Boolean>()

//    override fun onCreate() {
//        TODO("Not yet implemented")
//    }

    fun onLogin() {
        if (checkInternetConnection())
            loggingIn.postValue(true)
        else
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
    }

}