package com.example.kasir.ui.home.dashboard

import com.example.kasir.Base.BasePresenter
import com.example.kasir.Base.BaseView
import com.example.kasir.response.category.CategoryResponse
import com.example.kasir.response.distribusi.DistribusiResponse
import com.example.kasir.response.menu.MenuResponse

interface HomeContract {
    interface View:BaseView{
        fun categorySuccess(categoryResponse: CategoryResponse)
        fun categoryFailure(message:String)
        fun distribusiSuccess(distribusiResponse: DistribusiResponse)
        fun distribusiFailure(message:String)
        fun menuSuccess(menuResponse: MenuResponse)
        fun menuFailure(message:String)
    }

    interface Presenter: HomeContract, BasePresenter{
        fun getHome()
        fun getDistribusi()
        fun menuSuccess(id_lokasi:Int,
                        id_dis:Int,
                        page:Int)
    }
}