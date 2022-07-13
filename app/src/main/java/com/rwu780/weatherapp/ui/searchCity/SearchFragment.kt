package com.rwu780.weatherapp.ui.searchCity


import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rwu780.weatherapp.databinding.FragmentSearchBinding
import com.rwu780.weatherapp.domain.model.City
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var _binding: FragmentSearchBinding

    val viewModel : SearchFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()

        collectUiState()
        return _binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initView()
    }

    private fun initView() {
        _binding.etSearch.setOnKeyListener { _, keyCode, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER){
                val keyword = _binding.etSearch.text.toString()
                if (keyword.isBlank()){
                    Toast.makeText(context, "Cannot search on blank", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.searchLocationWithKeyword(keyword)
                }
                true
            } else false
        }

        _binding.rvSearchResult.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    private fun bindView(data: List<City>) {

        val adapter =  SearchCityAdapter(data) {
            viewModel.saveLocation(it)
            findNavController().navigateUp()
        }
        _binding.rvSearchResult.adapter = adapter

    }

    private fun initToolbar() {
        _binding.appBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onResume() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onResume()
    }

    private fun collectUiState() {
        lifecycleScope.launchWhenCreated {
            viewModel.searchState.collect { result ->
                when (result){
                    is SearchFragmentUiState.Success -> {
                        successStates(result.data)
                    }
                    is SearchFragmentUiState.Error -> errorState()
                    is SearchFragmentUiState.Loading -> loadingState()
                    else -> Unit
                }
            }
        }
    }

    private fun loadingState() {
        _binding.progressBar.visibility = View.VISIBLE

    }

    private fun errorState() {
        _binding.progressBar.visibility = View.GONE
        Toast.makeText(
            context,
            "Unable to find a match, please search with a different keyword",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun successStates(data: List<City>) {
        _binding.progressBar.visibility = View.GONE
        bindView(data)
    }
}