package com.example.paginacionexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paginacionexample.datasource.MainDataSource
import com.example.paginacionexample.pojos.MainData
import com.example.paginacionexample.repository.MainRepository

class MainViewModel(repository: MainRepository) : ViewModel() {

    lateinit var response: MutableLiveData<ArrayList<MainData>>

    var mainDataSource: MainDataSource = repository.requestMainDataSource()
    var page = 0

    init {
        response = mainDataSource.respuesta

    }

    fun getData() {
        mainDataSource.requestGetImages(page++, 12)
    }

}