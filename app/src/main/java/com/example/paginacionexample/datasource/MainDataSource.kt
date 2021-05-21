package com.example.paginacionexample.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.paginacionexample.Retrofit.MainAPI
import com.example.paginacionexample.pojos.MainData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainDataSource(private val mainApi: MainAPI) {
    var respuesta = MutableLiveData<ArrayList<MainData>>()

    companion object {
        const val ELEMENTS_PER_PAGE = 12
    }

    fun requestGetImages(page: Int) {

        mainApi.getImagesRequest(page, ELEMENTS_PER_PAGE)
            .enqueue(object : Callback<List<MainData>> {
                override fun onResponse(
                    call: Call<List<MainData>>,
                    response: Response<List<MainData>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("response", response.body().toString())
                        respuesta.postValue(response.body() as ArrayList<MainData>?)
                    }
                }

                override fun onFailure(call: Call<List<MainData>>, t: Throwable) {

                }

            })
    }


}