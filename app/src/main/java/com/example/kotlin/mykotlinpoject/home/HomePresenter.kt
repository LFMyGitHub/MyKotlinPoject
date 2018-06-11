package com.example.kotlin.mykotlinpoject.home

import android.util.Log
import com.example.kotlin.mykotlinpoject.dtos.ForecastDTO
import com.google.gson.Gson
import java.net.URL

class HomePresenter(homeView: HomeContract.View): HomeContract.Presenter {
    var mHomeView: HomeContract.View = homeView

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    init {
        mHomeView.setPresenter(this)
    }

    /**
     * 请求数据
     */
    override fun executeDate(zipCode: String): ForecastDTO.ForecastList {
        Log.i("TAG", "in executeDate：" + (COMPLETE_URL + zipCode))
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.i("TAG", forecastJsonStr)
        val forecastResult: ForecastDTO.ForecastResult  = Gson().fromJson(forecastJsonStr, ForecastDTO.ForecastResult::class.java)
        return ForecastDTO().convertFromDataModel(forecastResult)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}