package com.example.kasir.ui.home.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kasir.R
import com.example.kasir.response.search.Data
import com.example.kasir.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_menu_list.view.*

class AdapterSearch(private val listCategory: List<Data>,
                    private val listener: (Data) -> Unit)
    : RecyclerView.Adapter<AdapterSearch.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSearch.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutView = layoutInflater.inflate(R.layout.item_menu_list, parent,false)
        return  ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: AdapterSearch.ViewHolder, position: Int) {
        holder.bindItem(listCategory[position],listener)
    }

    override fun getItemCount(): Int = listCategory.size
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(data: Data, listener: (Data) -> Unit) {
            itemView.apply {

                tvName.text = data.nmMenu
                tvPrice.formatPrice(data.harga.toString())

                btnCart.setOnClickListener {
                    listener(data)
                }
            }
        }

    }


}