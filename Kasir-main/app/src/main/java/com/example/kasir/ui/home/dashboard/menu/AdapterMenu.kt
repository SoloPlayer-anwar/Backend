package com.example.kasir.ui.home.dashboard.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kasir.R
import com.example.kasir.response.menu.Data
import com.example.kasir.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_menu_list.view.*

class AdapterMenu(private val listMenu: List<Data>,
                  private val listener: (Data)-> Unit)
    :RecyclerView.Adapter<AdapterMenu.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMenu.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutView = layoutInflater.inflate(R.layout.item_menu_list, parent,false)
        return  ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: AdapterMenu.ViewHolder, position: Int) {
        holder.bindItem(listMenu[position],listener)
    }

    override fun getItemCount(): Int = listMenu.size
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
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