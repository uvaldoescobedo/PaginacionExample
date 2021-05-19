package com.example.paginacionexample.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.paginacionexample.databinding.ItemListRowBinding
import com.example.paginacionexample.pojos.MainData

class AdapterViewHolder(
    val binding: ItemListRowBinding,
    val actionClick: (item_list: MainData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    var item: MainData? = null

    init {
        binding.contentItem.setOnClickListener {
            item?.let { actionClick.invoke(it) }
        }

    }

    fun bind(item: MainData) {
        this.item = item
        with(item) {
            binding.apply {
                txtItem.text = item.author
                // con picasso se alenta la interfaz ni poniendole la politica funciona bien
                /*  Picasso.get().load(item.download_url)
                      .noFade()
                      .memoryPolicy(MemoryPolicy.NO_CACHE)
                      .networkPolicy(NetworkPolicy.NO_CACHE)
                      .into(binding.imageViewItem)*/

                Glide.with(binding.root.context).load(item.download_url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewItem)

            }
        }
    }
}