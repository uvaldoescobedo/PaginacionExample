package com.example.paginacionexample.Retrofit

import com.example.paginacionexample.pojos.MainData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MainAPI {
    @GET("v2/list")
    fun getImagesRequest(@Query("page") page:Int ,@Query("limit") limit:Int): Call<List<MainData>>
}