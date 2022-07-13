package com.rwu780.weatherapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rwu780.weatherapp.databinding.ItemHourForcastBinding
import com.rwu780.weatherapp.domain.model.HourlyForecast

class HourlyWeatherAdapter(
    private val dataItem : List<HourlyForecast>
) : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder>() {

    class HourlyWeatherViewHolder(
        private val _binding: ItemHourForcastBinding
    ): RecyclerView.ViewHolder(_binding.root){

        fun bind(item: HourlyForecast){
            _binding.ivIcon.setImageResource(getIconBasedOnWeatherStatus(item.status_icon))
            _binding.tvTemperature.text = item.temperature
            _binding.tvHour.text = item.getHour()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        return HourlyWeatherViewHolder(
            ItemHourForcastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(dataItem[position])
    }

    override fun getItemCount() = dataItem.size
}