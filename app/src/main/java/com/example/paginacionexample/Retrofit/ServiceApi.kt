package com.example.paginacionexample.Retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceApi {

    companion object {
        const val BASE_URL = "https://picsum.photos/"
    }

    private var retrofit: Retrofit? = null

    fun getMainAPI(): MainAPI {
        return getClient()!!.create(
            MainAPI::class.java
        )
    }


    fun getClient(): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}
