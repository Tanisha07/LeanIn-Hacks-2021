package com.tani.foodfighters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun start(view: View){
        var intent = Intent(applicationContext, start::class.java)
        startActivity(intent)
    }

    fun signUp(view: View){
        var intent = Intent(applicationContext, signup::class.java)
        startActivity(intent)
    }


}