package com.example.foodfightersnew

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle

//class MainActivity2 : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
//    }
//}

//package com.tani.foodfighters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun start(view: View){
        var intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    fun signUp(view: View){
        var intent = Intent(applicationContext, signup::class.java)
        startActivity(intent)
    }




}