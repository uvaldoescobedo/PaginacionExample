package com.example.paginacionexample.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.example.paginacionexample.Retrofit.ServiceApi
import com.example.paginacionexample.adapter.MainAdapter
import com.example.paginacionexample.databinding.ActivityMainBinding
import com.example.paginacionexample.pojos.MainData
import com.example.paginacionexample.repository.MainRepository
import com.example.paginacionexample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MainAdapter
    lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = MainViewModel(MainRepository(ServiceApi().getMainAPI()))
        initElements()
        //carga inicial de elementos
        model.getData()
        observers()

    }

    fun initElements() {
        adapter = MainAdapter(arrayListOf(), ::actionClickOnItem)
        binding.apply {
            recycler.adapter = this@MainActivity.adapter

            nedScrool.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (v != null) {
                    if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                        // cuando el ultimo item se esta viendo incrementamos el tamaño de pagina y mandamos la peticion
                        model.getData()

                    }
                }
            })
        }
    }

    private fun actionClickOnItem(mainData: MainData) {
        Toast.makeText(baseContext, "${mainData.author}", Toast.LENGTH_SHORT).show()
    }

    private fun observers() {
        model.response.observe(this, Observer {
            if (it != null) {
                adapter.addElementToView(it)
            }
        })
    }

}