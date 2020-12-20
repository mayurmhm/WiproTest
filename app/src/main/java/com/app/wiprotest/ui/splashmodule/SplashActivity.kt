package com.app.wiprotest.ui.splashmodule

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.app.wiprotest.R
import com.app.wiprotest.base.BaseActivity
import com.app.wiprotest.databinding.ActivitySplashBinding
import com.app.wiprotest.ui.mainmodule.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Activity for the Splash Screen
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    /**
     * Instance of [SplashViewModel]
     */
    private val splashViewModel: SplashViewModel by viewModel()

    /**
     * Instance of [ActivitySplashBinding]
     */
    var activitySplashBinding: ActivitySplashBinding? = null

    /**
     * Instance of [Handler]
     */
    var handler: Handler? = null

    /**
     * Stores duration for splash screen
     */
    private val mSplashTimeOut = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialization
        handler = Handler()
        handler!!.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, mSplashTimeOut)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
        return splashViewModel
    }

    override fun setUp(savedInstanceState: Bundle?) {
        activitySplashBinding = getViewDataBinding()
    }
}