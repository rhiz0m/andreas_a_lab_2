package com.rhiz0me.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.rhiz0me.myapplication.R
import com.rhiz0me.myapplication.databinding.FragmentHomeBinding
import com.rhiz0me.myapplication.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Setup viewBinding
        binding = FragmentHomeBinding.inflate(layoutInflater,  container, false)
        val view = binding.root

        //viewModel Lifecycle
        lifecycleScope.launch {
               viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                   homeViewModel.uiState.collect() {
                       //Update UI elements
                       binding.tvDate.text = homeViewModel.uiState.value.toString()
                   }
               }
        }

        //ID's

        val btnAbout = binding.btnAbout
        val btnFunctionality = binding.btnFunctionality
        val btnNews = binding.btnNews
        val btnUpdateDate = binding.btnUpdateDate

        //Onclick

        btnAbout.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutFragment)
        }

        btnFunctionality.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_functionalityFragment)
        }

        btnNews.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_newsletterFragment)
        }

        btnUpdateDate.setOnClickListener() {
            homeViewModel.add()
        }

        return view
    }

}