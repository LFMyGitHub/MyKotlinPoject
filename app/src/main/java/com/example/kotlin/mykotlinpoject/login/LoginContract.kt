package com.example.kotlin.mykotlinpoject.login

import com.example.kotlin.mykotlinpoject.base.BasePresenter
import com.example.kotlin.mykotlinpoject.base.BaseView

interface LoginContract {
    interface View: BaseView<Presenter> {
        fun loginSuccess()
    }

    interface Presenter: BasePresenter {
        fun login(name: String, password: String)
    }
}