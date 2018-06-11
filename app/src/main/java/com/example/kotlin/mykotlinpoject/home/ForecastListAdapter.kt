package com.example.ex_longfeng.mykotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin.mykotlinpoject.R
import com.example.kotlin.mykotlinpoject.dtos.ForecastDTO
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class ForecastListAdapter(val weekForecast: ForecastDTO.ForecastList, val itemClick: OnItemClickListener):
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size()

    interface OnItemClickListener {
        operator fun invoke(forecast: ForecastDTO.ForecastDesc)
    }

    class ViewHolder(view: View, val itemClick: OnItemClickListener): RecyclerView.ViewHolder(view) {
        private val iconView: ImageView
        private val dateView: TextView
        private val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTemperatureView: TextView

        init {
            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTemperatureView = view.find(R.id.minTemperature)
        }

        fun bindForecast(forecast: ForecastDTO.ForecastDesc) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.setOnClickListener { itemClick(forecast) }
            }
        }
    }
}

