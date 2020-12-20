package com.app.wiprotest.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import com.app.wiprotest.R
import es.dmoral.toasty.Toasty

/**
 * Network helper class which observers network events
 */
class NetworkHelper : Activity() {
    /**
     * Returns network is connected or not
     */
    fun isConnected(context: Context): Boolean {
        val manager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        return info != null && info.isConnected
    }

    /**
     * Displays Network Status
     */
    fun showNetworkToastMessage(context: Context?) {
        context?.let { Toasty.warning(it, R.string.no_internet, Toasty.LENGTH_LONG).show() }
    }

    /**
     * Companion Object
     */
    companion object {
        private var networkHelper: NetworkHelper? = null

        @JvmStatic
        val networkHelperInstance: NetworkHelper?
            get() {
                if (networkHelper == null) {
                    networkHelper = NetworkHelper()
                }
                return networkHelper
            }
    }
}