package com.example.kasir.ui.home.search

import com.example.kasir.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter(private val view: SearchContract.View):SearchContract.Presenter {
    private val mCompositeDisposable : CompositeDisposable?
    init {
        mCompositeDisposable = CompositeDisposable()
    }

    override fun getSearch(id_lokasi: Int, id_dis: Int) {
        view.showLoading(true)
        val disposable = HttpClient.getInstance().getApi()!!.search(
            id_lokasi, id_dis
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it.meta.status.equals("success", true)) {
                    true -> {
                        view.searchSuccess(it)
                        view.showLoading(false)
                    }

                    false -> {
                        view.searchFailure(it.meta.message)
                        view.showLoading(false)
                    }
                }
            }, {
                view.searchFailure(it.message.toString())
                view.showLoading(false)
            })

        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }


}