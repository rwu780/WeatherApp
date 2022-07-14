package com.rwu780.weatherapp.ui.dashboard

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rwu780.weatherapp.R
import com.rwu780.weatherapp.databinding.FragmentDashboardBinding
import com.rwu780.weatherapp.domain.model.CurrentWeather
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var _binding : FragmentDashboardBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadCityName()
        }

        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ))

    }

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

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        collectUiStates()
    }

    override fun onResume() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        viewModel.loadCityName()
        viewModel.loadUnits()
        super.onResume()
    }

    private fun initView() {

        viewModel.cityName.observe(viewLifecycleOwner) {
            viewModel.fetchWeather(it)
        }

        _binding.iconSetting.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment)
        }

        _binding.iconSearch.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_searchFragment)
        }

        _binding.iconLocation.setOnClickListener {
            viewModel.fetchCurrentLocation()
        }

        _binding.tvCityHeader.setOnClickListener {
            val mapIntent: Intent = Intent(Intent.ACTION_VIEW).apply {
                this.data = Uri.parse(
                    "geo:0,0?q=${_binding.tvCityHeader.text}"
                )
            }
            startActivity(mapIntent)
        }

        _binding.rvHourlyForecast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        _binding.rvDailyForecast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    private fun bindView(currentWeather: CurrentWeather){
        _binding.tvCityHeader.text = currentWeather.city.name
        _binding.tvWeatherDescription.text = currentWeather.current_status
        _binding.tvCurrentWeather.text = currentWeather.getCurrentTemperature(viewModel.temperatureUnits)
        _binding.iconCurrentWeather.setImageResource(getIconBasedOnWeatherStatus(currentWeather.current_status))

        val hourlyWeatherAdapter = HourlyWeatherAdapter(currentWeather.hourlyForecast, viewModel.temperatureUnits)
        _binding.rvHourlyForecast.adapter = hourlyWeatherAdapter

        val dailyWeatherAdapter = DailyWeatherAdapter(currentWeather.dailyForecast, viewModel.temperatureUnits)
        _binding.rvDailyForecast.adapter = dailyWeatherAdapter

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
        bindView(currentWeather)
        _binding.progressBar.visibility = View.GONE
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

        _binding.rvDailyForecast.adapter = null
        _binding.rvHourlyForecast.adapter = null

        _binding.tvCityHeader.text = ""
        _binding.tvCurrentWeather.text = "-- --"
    }
}