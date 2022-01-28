package com.example.kasir.ui

import com.example.kasir.Base.BasePresenter
import com.example.kasir.Base.BaseView
import com.example.kasir.response.sign.LoginResponse

interface LoginContract {
    interface View: BaseView {
        fun loginSuccess(loginResponse: LoginResponse)
        fun loginFailure(message:String)
    }

    interface Presenter: LoginContract, BasePresenter {
        fun submitLogin(email:String, password:String)
    }
}