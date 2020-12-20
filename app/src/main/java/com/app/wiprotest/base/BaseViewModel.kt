package com.app.wiprotest.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Generic Base View Model
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Instance of Composite Disposable
     */
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}