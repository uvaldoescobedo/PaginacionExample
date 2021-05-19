package com.example.paginacionexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paginacionexample.databinding.ItemListRowBinding
import com.example.paginacionexample.pojos.MainData

class MainAdapter(private var lista: ArrayList<MainData>, val actionClick: (item_list: MainData) -> Unit) :
    RecyclerView.Adapter<AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemListRowBinding.inflate(layoutInflater, parent, false)
        return AdapterViewHolder(itemBinding, actionClick)

    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val item = lista[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun addElementToView(list: ArrayList<MainData>) {
        //   this.lista = list
        this.lista.addAll(list)
        notifyDataSetChanged()
    }


}