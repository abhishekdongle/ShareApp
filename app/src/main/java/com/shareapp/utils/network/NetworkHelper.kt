package com.shareapp.utils.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Singleton

@Singleton
class NetworkHelper(private val context: Context) {

    fun isNetworkConnected(): Boolean {
        var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}