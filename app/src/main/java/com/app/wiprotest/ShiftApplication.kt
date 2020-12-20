package com.app.wiprotest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

/**
 * The Application Class
 */
class ShiftApplication : Application() {

    init {
        shiftApplication = this
    }

    override fun onCreate() {
        super.onCreate()

        /**
         * Start KOIN
         */
        startKoin {
            androidContext(this@ShiftApplication)
            androidFileProperties()
        }
        injectFeature()
    }

    companion object {
        /**
         * Instance of [ShiftApplication]
         */
        lateinit var shiftApplication: ShiftApplication
    }
}