package com.example.paginacionexample.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginacionexample.Retrofit.ServiceApi
import com.example.paginacionexample.adapter.MainAdapter
import com.example.paginacionexample.databinding.ActivityMainBinding
import com.example.paginacionexample.pojos.MainData
import com.example.paginacionexample.repository.MainRepository
import com.example.paginacionexample.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), MainAdapter.OnClickListenerHelper {
    lateinit var binding: ActivityMainBinding
    var lista: ArrayList<MainData> = arrayListOf()
    lateinit var adapter: MainAdapter
    lateinit var model: MainViewModel
    var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = MainViewModel(MainRepository(ServiceApi().getMainAPI()))

        adapter = MainAdapter(lista, this,this)
        binding.recycler.adapter = adapter
        //carga inicial de elementos
        model.getData(page, 12)
        observers()

        binding.nedScrool.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (v != null) {
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    // cuando el ultimo item se esta viendo incrementamos el tamaño de pagina y mandamos la peticion
                    model.getData(++page, 6)

                }
            }
        })

    }

    private fun isLastItemDisplaying(recyclerView: RecyclerView): Boolean {
        if (recyclerView.adapter!!.itemCount != 0) {
            val lastVisibleItemPosition =
                (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.adapter!!
                    .itemCount - 1
            ) return true
        }
        return false
    }

    private fun observers() {

        model.response.observe(this, Observer {
            if (it != null) {
                adapter.addElementToView(it)
            }
        })

    }

    override fun onClickListener(item: MainData) {
        Toast.makeText(baseContext, "diste click en ${item.author}", Toast.LENGTH_SHORT).show()
    }
}