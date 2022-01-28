package com.example.kasir.ui.home.search

import com.example.kasir.Base.BasePresenter
import com.example.kasir.Base.BaseView
import com.example.kasir.response.search.SearchResponse

interface SearchContract {
    interface View : BaseView {
        fun searchSuccess(searchResponse: SearchResponse)
        fun searchFailure(message:String)
    }

    interface Presenter: SearchContract, BasePresenter {
        fun getSearch(id_lokasi: Int, id_dis: Int)
    }
}