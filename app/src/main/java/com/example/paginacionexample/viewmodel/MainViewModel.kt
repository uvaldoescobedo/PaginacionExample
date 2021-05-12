package com.example.paginacionexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paginacionexample.datasource.MainDataSource
import com.example.paginacionexample.pojos.MainData
import com.example.paginacionexample.repository.MainRepository

class MainViewModel(repository: MainRepository) : ViewModel() {

    lateinit var response: MutableLiveData<ArrayList<MainData>>

    var mainDataSource: MainDataSource = repository.requestMainDataSource()

    fun getData(page: Int, limit: Int) {
        response = mainDataSource.respuesta
        mainDataSource.requestGetImages(page, limit)

    }

}