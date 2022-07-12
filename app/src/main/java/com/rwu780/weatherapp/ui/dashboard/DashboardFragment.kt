package com.rwu780.weatherapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rwu780.weatherapp.R
import com.rwu780.weatherapp.databinding.FragmentDashboardBinding
import com.rwu780.weatherapp.domain.model.CurrentWeather
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var _binding : FragmentDashboardBinding

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(
            inflater,
            container,
            false
        )
        collectUiStates()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    override fun onResume() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onResume()
    }

    private fun initView() {

        viewModel.fetchWeather("Nanaimo,BC")

        _binding.iconSetting.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment)
        }

        _binding.iconSearch.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_searchFragment)
        }

    }

    private fun bindView(currentWeather: CurrentWeather){
        _binding.tvCityHeader.text = currentWeather.city.name
        _binding.tvCurrentWeather.text = currentWeather.current_temperature
        _binding.iconCurrentWeather.setImageResource(getIconBasedOnWeatherStatus(currentWeather.current_status))

    }

    private fun collectUiStates() {
        lifecycleScope.launchWhenCreated {
            viewModel.dashboardState.collect{
                when (it) {
                    is DashboardUiState.Success -> successStates(it.data)
                    is DashboardUiState.Error -> errorStates(it.errorMsg)
                    is DashboardUiState.Loading -> loadingStates()
                    else -> Unit
                }
            }
        }
    }

    private fun successStates(currentWeather: CurrentWeather) {
        _binding.progressBar.visibility = View.GONE
        bindView(currentWeather)
    }

    private fun loadingStates() {
        _binding.progressBar.visibility = View.VISIBLE

    }

    private fun errorStates(errorMsg: String) {
        _binding.progressBar.visibility = View.GONE
        Snackbar.make(
            _binding.root,
            errorMsg,
            Snackbar.LENGTH_SHORT
        ).show()

        _binding.tvCityHeader.text = ""
        _binding.tvCurrentWeather.text = "-- --"
    }


}