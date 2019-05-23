package com.smarthome.controller.api

import com.smarthome.controller.models.ApiResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SmartHomeApi {

    @GET("endpoint1")
    fun action1(@Query("param1") param1: String): Observable<ApiResult>

    @GET("endpoint2")
    fun action2(@Query("param1") param1: String): Observable<ApiResult>

    @GET("endpoint3")
    fun action3(@Query("param1") param1: String): Observable<ApiResult>

    @GET("endpoint4")
    fun action4(@Query("param1") param1: String): Observable<ApiResult>

    @GET("endpoint5")
    fun action5(@Query("param1") param1: String): Observable<ApiResult>

    @GET("endpoint6")
    fun action6(@Query("param1") param1: String): Observable<ApiResult>
}