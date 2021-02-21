package com.example.foodfightersnew

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class signup : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_signup)
//    }
//}


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class signup : AppCompatActivity() {
    var userType = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    fun viewDetails(view: View){
        if(userType==-1){
            Toast.makeText(applicationContext, "Select an option first", Toast.LENGTH_LONG).show()
        }
        else {
            val intent = Intent(applicationContext, datails::class.java)
            intent.putExtra("user", userType)
            startActivity(intent)
        }
    }

    fun donor(view: View){
        userType = 1
    }

    fun dist(view: View){
        userType = 2
    }

}