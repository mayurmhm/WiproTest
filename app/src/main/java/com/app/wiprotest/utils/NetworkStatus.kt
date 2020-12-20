package com.app.wiprotest.utils

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.wiprotest.R
import com.app.wiprotest.databinding.ActivityNetworkStatusBinding
import com.app.wiprotest.ui.mainmodule.MainActivity
import com.app.wiprotest.utils.NetworkHelper.Companion.networkHelperInstance

/**
 * A class which observes network status
 */
class NetworkStatus : AppCompatActivity(), View.OnClickListener {

    /**
     * Instance of [ActivityNetworkStatusBinding]
     */
    private var networkStatusBinding: ActivityNetworkStatusBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        // initialization
        networkStatusBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_network_status)
        networkStatusBinding?.btnRetry?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (networkHelperInstance!!.isConnected(applicationContext)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            networkHelperInstance!!.showNetworkToastMessage(applicationContext)
        }
    }
}