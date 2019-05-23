package com.smarthome.controller.api

import android.content.Context
import android.text.TextUtils
import com.smarthome.controller.settings.SettingManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object SmartHomeApiClient {
    // If you need to add auth params you can create and Interceptor and add it to this client
    private val httpClient = OkHttpClient().newBuilder().build()

    fun getApi(context: Context): SmartHomeApi? {
        val ipAddress = SettingManager.getIPAddress(context)

        if (ipAddress == null || TextUtils.isEmpty(ipAddress)) {
            return null
        } else {
            val baseUrl = "http://$ipAddress/"

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .baseUrl(baseUrl)
                .build()
            return retrofit.create(SmartHomeApi::class.java)
        }
    }
}