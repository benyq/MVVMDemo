package com.benyq.mvvmdemo

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/3
 */
object HttpHelper {

    private var httpClient: OkHttpClient
    private var retrofit: Retrofit
    private var apiService: ApiService

    init {
         httpClient = OkHttpClient.Builder().let {
            it.addNetworkInterceptor(HttpLoggingInterceptor { message -> Log.i("okhttp", message) }.setLevel(HttpLoggingInterceptor.Level.BODY))
            it.connectTimeout(10, TimeUnit.SECONDS)
            it.readTimeout(10, TimeUnit.SECONDS)
            it.writeTimeout(10, TimeUnit.SECONDS)
            //错误重连
            it.retryOnConnectionFailure(true)
            it.build()
        }

        retrofit = Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .setPrettyPrinting()
                        .disableHtmlEscaping()
                        .create()))
                .build()

        apiService = retrofit.create(ApiService::class.java)
    }


}