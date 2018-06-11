package com.example.kotlin.mykotlinpoject.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.ex_longfeng.mykotlin.adapter.ForecastListAdapter
import com.example.kotlin.mykotlinpoject.R
import com.example.kotlin.mykotlinpoject.dtos.ForecastDTO
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class HomeActivity: AppCompatActivity(), View.OnClickListener, HomeContract.View {
    private var homePresenter: HomeContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var forecast: ForecastDTO.ForecastList
        rvHome.layoutManager = LinearLayoutManager(this)
        homePresenter = HomePresenter(this@HomeActivity)
        btnHome.setOnClickListener(this)
    }

    fun requestData(string: String) {
        async {
            val result = homePresenter!!.executeDate(string)
            uiThread {
                rvHome.adapter = ForecastListAdapter(result, object: ForecastListAdapter.OnItemClickListener {
                    override fun invoke(forecast: ForecastDTO.ForecastDesc) {
                        toast(forecast.date)
                    }
                })
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.btnHome -> requestData(etHome.text.toString()!!)
        }
    }

    override fun setPresenter(presenter: HomeContract.Presenter) {
        homePresenter = presenter
    }
}