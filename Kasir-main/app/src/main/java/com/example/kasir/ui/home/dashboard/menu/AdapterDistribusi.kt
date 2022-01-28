package com.example.kasir.ui.home.dashboard.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasir.R
import com.example.kasir.response.distribusi.Data
import kotlinx.android.synthetic.main.item_distribusi.view.*

class AdapterDistribusi(private val listDistrubis: List<Data>,
                        private val itemAdapterCallback: ItemAdapterCallback)
    :RecyclerView.Adapter<AdapterDistribusi.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDistribusi.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutView = layoutInflater.inflate(R.layout.item_distribusi,parent,false)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: AdapterDistribusi.ViewHolder, position: Int) {
        holder.bindItem(listDistrubis[position],itemAdapterCallback)
    }

    override fun getItemCount(): Int = listDistrubis.size
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItem(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvNameDistribusi.text = data.nmDistribusi
            }

            itemView.setOnClickListener {
                itemAdapterCallback.onClick(it,data)
            }
        }

    }

    interface ItemAdapterCallback {
        fun onClick(v:View,data: Data)
    }
}