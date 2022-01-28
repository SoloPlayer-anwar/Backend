package com.example.kasir.ui.home.dashboard

import com.example.kasir.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeContract.View):HomeContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable?
    init {
        mCompositeDisposable = CompositeDisposable()
    }

    override fun getHome() {
        val disposable = HttpClient.getInstance().getApi()!!.category()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it.meta.status.equals("success", true)) {
                    true -> {
                        view.categorySuccess(it)

                    }

                    false -> {
                        view.categoryFailure(it.meta.message)
                    }
                }
            }, {
                view.categoryFailure(it.message.toString())
            })

        mCompositeDisposable!!.add(disposable)
    }


    override fun getDistribusi() {
        val disposable = HttpClient.getInstance().getApi()!!.distribusi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it.meta.status.equals("success", true)) {
                    true -> {
                        view.distribusiSuccess(it)
                    }

                    false -> {
                        view.distribusiFailure(it.meta.message)
                    }
                }
            }, {
                view.distribusiFailure(it.message.toString())
            })

        mCompositeDisposable!!.add(disposable)
    }

    override fun menuSuccess(id_lokasi: Int, id_dis: Int, page: Int) {
        view.showLoading(true)
        val disposable = HttpClient.getInstance().getApi()!!.menu(
            id_lokasi, id_dis, page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it.meta.status.equals("success", true)) {
                    true -> {
                        view.menuSuccess(it)
                        view.showLoading(false)
                    }

                    false -> {
                        view.menuFailure(it.meta.message)
                        view.showLoading(false)
                    }
                }
            }, {
                view.menuFailure(it.message.toString())
                view.showLoading(false)
            })

        mCompositeDisposable!!.add(disposable)
    }


    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }


}