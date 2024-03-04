package com.cremasfood.app.utils.internet

import android.content.Context
import android.net.ConnectivityManager

object CheckInternet {
    fun isInternetAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return cm?.activeNetworkInfo != null
    }
}