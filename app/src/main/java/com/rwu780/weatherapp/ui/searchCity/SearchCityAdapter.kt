package com.rwu780.weatherapp.ui.searchCity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rwu780.weatherapp.databinding.ItemCityResultBinding
import com.rwu780.weatherapp.domain.model.City

class SearchCityAdapter(
    private val dataItems: List<City>,
    private val onClickEvent: (City) -> Unit
) : RecyclerView.Adapter<SearchCityAdapter.SearchCityViewHolder>() {

    class SearchCityViewHolder(
        private val _binding: ItemCityResultBinding
    ) : RecyclerView.ViewHolder(_binding.root){

        fun bind(item: City, onClickEvent: (City) -> Unit){
            _binding.tvCityName.text = "${item.name}, ${item.region}"
            _binding.root.setOnClickListener {
                onClickEvent(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCityViewHolder {
        return SearchCityViewHolder(
            ItemCityResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchCityViewHolder, position: Int) {
        holder.bind(dataItems[position], onClickEvent)
    }

    override fun getItemCount() = dataItems.size
}