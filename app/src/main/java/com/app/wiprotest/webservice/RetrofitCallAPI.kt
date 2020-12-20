package com.app.wiprotest.webservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * A class responsible for making API calls using Retrofit
 */
class RetrofitCallAPI {

    companion object {

        /**
         * Instance of [RetrofitAPInterface]
         */
        private var retrofitAPInterfaceRx: RetrofitAPInterface? = null

        /**
         * OKHTTPClient Builder
         */
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        /**
         * This builds API Call
         */
        fun getInstanceRx(baseUrl: String): RetrofitAPInterface? {
            if (retrofitAPInterfaceRx == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

                retrofitAPInterfaceRx = retrofit.create(RetrofitAPInterface::class.java)
            }
            return retrofitAPInterfaceRx
        }
    }
}