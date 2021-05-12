package com.example.paginacionexample.repository

import com.example.paginacionexample.Retrofit.MainAPI
import com.example.paginacionexample.datasource.MainDataSource

class MainRepository(private val mainApi:MainAPI) {

    fun requestMainDataSource():MainDataSource{
        return MainDataSource(mainApi)
    }
}