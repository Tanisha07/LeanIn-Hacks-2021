package com.example.foodfightersnew.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foodfightersnew.MainActivity
import com.example.foodfightersnew.MainActivity2
import com.example.foodfightersnew.R
import com.example.foodfightersnew.donate

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)
        // connecting to button8 from xml file
        val button = root.findViewById<Button>(R.id.button8)
//        setting onClick listener
        button.setOnClickListener {
//             intent to donation act
            val intent = Intent(activity, donate::class.java)
            activity?.startActivity(intent)
        }
        return root
    }



//    fun donate(view: View){
//        val intent = Intent(activity, MainActivity2::class.java)
//        activity?.startActivity(intent)
//    }

}