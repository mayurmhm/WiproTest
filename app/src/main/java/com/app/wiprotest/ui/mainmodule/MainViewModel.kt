package com.app.wiprotest.ui.mainmodule

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.wiprotest.base.BaseViewModel
import com.app.wiprotest.model.api.CityInformationModel
import com.app.wiprotest.webservice.WebServiceAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import com.app.wiprotest.webservice.RetrofitCallAPI

/**
 * View Model for the [MainActivity]
 */
class MainViewModel(application: Application) : BaseViewModel(application) {

    /**
     * Instance of [MainNavigator]
     */
    var mainNavigator: MainNavigator? = null

    /**
     * Function which requests data from the API
     */
    fun callApi(): LiveData<CityInformationModel> {
        val loginModel = MutableLiveData<CityInformationModel>()

        RetrofitCallAPI.getInstanceRx(WebServiceAPI.SERVER_BASE_URL)!!
            .getAllItemsResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<CityInformationModel>() {
                override fun onComplete() {
                }

                override fun onNext(t: CityInformationModel) {
                    loginModel.value = t
                }

                override fun onError(e: Throwable) {
                    Log.e("onError", e.message.toString())
                    mainNavigator?.onError()
                }
            })
        return loginModel
    }

}