package com.example.kasir.ui.home.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasir.R
import com.example.kasir.response.category.Data
import kotlinx.android.synthetic.main.item_category_list.view.*

class AdapterHome(private val listCategory: List<Data>,
                  private val itemAdapterCallback:ItemAdapterCallback)
    :RecyclerView.Adapter<AdapterHome.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHome.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutView = layoutInflater.inflate(R.layout.item_category_list, parent,false)
        return  ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: AdapterHome.ViewHolder, position: Int) {
        holder.bindItem(listCategory[position],itemAdapterCallback)
    }

    override fun getItemCount(): Int = listCategory.size
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bindItem(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvCategory.text = data.kategori

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it,data)
                }
            }
        }

    }

    interface ItemAdapterCallback {
        fun onClick(v:View, data: Data)
    }
}