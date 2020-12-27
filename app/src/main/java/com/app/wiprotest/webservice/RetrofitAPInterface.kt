package com.app.wiprotest.webservice

import com.app.wiprotest.model.api.CityInformationModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Interface for Retrofit
 */
interface RetrofitAPInterface {
    @GET(WebServiceAPI.GET_CANADA_INFO)
    fun getAllItemsResponse(): Observable<CityInformationModel>
}