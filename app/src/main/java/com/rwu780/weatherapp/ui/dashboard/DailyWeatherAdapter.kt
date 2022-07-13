package com.rwu780.weatherapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rwu780.weatherapp.databinding.ItemDailyForecastBinding
import com.rwu780.weatherapp.domain.Temperature_Units
import com.rwu780.weatherapp.domain.model.DailyForecast

class DailyWeatherAdapter(
    private val dataItems: List<DailyForecast>,
    private val units: Temperature_Units
) : RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder>() {

    class DailyWeatherViewHolder(
        private val _binding: ItemDailyForecastBinding
    ) : RecyclerView.ViewHolder(_binding.root){

        fun bind(item: DailyForecast, units: Temperature_Units){
            _binding.tvDate.text = item.date
            _binding.tvDayHigh.text = item.getDayHigh(units)
            _binding.tvDayLow.text = item.getDayLow(units)
            _binding.iconWeather.setImageResource(getIconBasedOnWeatherStatus(item.status_icon))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        return DailyWeatherViewHolder(
            ItemDailyForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(dataItems[position], units)
    }

    override fun getItemCount() = dataItems.size
}