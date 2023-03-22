package com.rhiz0me.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.rhiz0me.myapplication.R
import com.rhiz0me.myapplication.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding : FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Setup viewBinding
        binding = FragmentAboutBinding.inflate(layoutInflater,  container, false)
        val view = binding.root

        //ID's

        val btnHome = binding.btnHome

        //Onclick

        btnHome.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_aboutFragment_to_homeFragment)
        }

        return view
    }

}