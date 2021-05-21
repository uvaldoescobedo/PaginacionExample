package com.example.paginacionexample.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paginacionexample.datasource.MainDataSource
import com.example.paginacionexample.pojos.MainData
import com.example.paginacionexample.repository.MainRepository

class MainViewModel(repository: MainRepository) : ViewModel() {

    lateinit var response: MutableLiveData<ArrayList<MainData>>
    var page = MutableLiveData<Int>()
    var mainDataSource: MainDataSource = repository.requestMainDataSource()


    init {
        response = mainDataSource.respuesta

    }

    fun getData() {
        page.value = (this@MainViewModel.page.value ?: 0) + 1
        mainDataSource.requestGetImages(page.value!!)
    }

}