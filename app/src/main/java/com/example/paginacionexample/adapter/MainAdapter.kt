package com.example.paginacionexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.paginacionexample.pojos.MainData
import com.example.paginacionexample.databinding.ItemListRowBinding
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class MainAdapter(
    private var lista: ArrayList<MainData>,
    private val onClickListenerHelper: OnClickListenerHelper, var contexto: Context
) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    class MyViewHolder private constructor(private var binding: ItemListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemBinding = ItemListRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(itemBinding)
            }
        }

        fun bind(item: MainData, onClickListenerHelper: OnClickListenerHelper, contexto: Context) {
            binding.txtItem.text = item.author
            // con picasso se alenta la interfaz ni poniendole la politica funciona bien
            /*  Picasso.get().load(item.download_url)
                  .noFade()
                  .memoryPolicy(MemoryPolicy.NO_CACHE)
                  .networkPolicy(NetworkPolicy.NO_CACHE)
                  .into(binding.imageViewItem)*/

            Glide.with(contexto).load(item.download_url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageViewItem)

            binding.contentItem.setOnClickListener { onClickListenerHelper.onClickListener(item) }
        }
    }

    interface OnClickListenerHelper {
        fun onClickListener(item: MainData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = lista[position]
        holder.bind(item, onClickListenerHelper, contexto)
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
