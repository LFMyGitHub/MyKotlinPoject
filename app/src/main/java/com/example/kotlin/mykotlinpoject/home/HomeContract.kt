package com.example.kotlin.mykotlinpoject.home

import com.example.kotlin.mykotlinpoject.base.BasePresenter
import com.example.kotlin.mykotlinpoject.base.BaseView
import com.example.kotlin.mykotlinpoject.dtos.ForecastDTO

interface HomeContract {
    interface View: BaseView<Presenter> {
    }

    interface Presenter: BasePresenter {
        fun executeDate(zipCode: String): ForecastDTO.ForecastList
    }
}