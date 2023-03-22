package com.rhiz0me.myapplication.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.rhiz0me.myapplication.R
import com.rhiz0me.myapplication.databinding.FragmentFunctionalityBinding
import com.rhiz0me.myapplication.model.Posts
import com.rhiz0me.myapplication.model.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FunctionalityFragment : Fragment() {

    private lateinit var binding: FragmentFunctionalityBinding
    private val baseURL = "https://jsonplaceholder.typicode.com/"
    private var postsArr = ArrayList<Posts>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Setup viewBinding
        binding = FragmentFunctionalityBinding.inflate(layoutInflater,  container, false)
        val view = binding.root

        //Button ID's

        val btnHome = binding.btnHome
        val apiBtn = binding.btnApi

        //Hiding API info container
        binding.apiInfoContainer.isVisible = false

        //Functions

        fun showPosts() {
            val retroFit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retroFitAPI : RetrofitAPI = retroFit.create(RetrofitAPI::class.java)

            //Creating an object from the Call interface
            val call : Call<List<Posts>> = retroFitAPI.getAllPosts()

            //Async call to server
            //Creating an callback function
            call.enqueue(object : Callback<List<Posts>> {
                override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {

                    //If not success
                    if(!response.isSuccessful) {
                        binding.tvUserIDAPI .text = "error..."
                        binding.tvIdAPI .text = "error..."
                        binding.tvTitleAPI.text = "error..."
                        binding.tvBodyAPI.text = "error..."
                    }

                    //If success
                    //All data in REST API gets transfered into the Arraylist asKotlin Objects.
                    postsArr = response.body() as ArrayList<Posts>

                    //Getting the third object in the JSON data array. Index nr 2.
                    binding.tvUserIDAPI.text = "This is the Users Id: " +  postsArr[2].userId.toString()
                    binding.tvIdAPI.text = "This is an Id: " + postsArr[2].id.toString()
                    binding.tvTitleAPI.text = "This is a title: " + postsArr[2].title
                    binding.tvBodyAPI.text = "This is a subtitle: " + postsArr[2].subtitle


                }

                override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

                    Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_LONG)
                }

            })

        }

        //Onclick

        btnHome.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_functionalityFragment_to_homeFragment)
        }

        apiBtn.setOnClickListener() {
            binding.apiInfoContainer.isVisible = true
            showPosts()
        }

        return view
    }

}