package com.example.manorma.testapplication.util

import android.content.Context
import android.net.ConnectivityManager

class AppUtil {

    companion object {
        fun isNetworkAvailable(context: Context):Boolean{
            var connectionManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectionManager.activeNetworkInfo?.isConnectedOrConnecting!!
        }
    }
}