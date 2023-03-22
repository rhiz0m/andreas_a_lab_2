package com.rhiz0me.myapplication.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.rhiz0me.myapplication.R
import com.rhiz0me.myapplication.databinding.FragmentNewsletterBinding


class NewsletterFragment : Fragment() {

    private lateinit var binding: FragmentNewsletterBinding
    private var emailArr = arrayListOf<Editable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Setup viewBinding
        binding = FragmentNewsletterBinding.inflate(layoutInflater,  container, false)
        val view = binding.root

        //ID's

        val btnHome = binding.btnHome
        val btnEmailNews = binding.btnEmailNews
        val getMail = binding.enterEmail.text


        //Onclick

        btnHome.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_newsletterFragment_to_homeFragment)
        }

        fun addEmail() {
            emailArr.add(getMail)
            for (item in emailArr) {
                binding.showEmail.text = "Your email: $item"
            }
        }

            val snackBar = Snackbar.make(binding.viewNewsletter, "Want us to save your email?", Snackbar.LENGTH_LONG).setAction("UNDO", View.OnClickListener {
            binding.showEmail.text = ""
        })

            btnEmailNews.setOnClickListener {
                addEmail()
                snackBar.show()
            }
        return view
    }
}