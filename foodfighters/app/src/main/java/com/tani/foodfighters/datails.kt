package com.tani.foodfighters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_datails.*

class datails : AppCompatActivity() {
    var type = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datails)

        type = intent.getIntExtra("user", -1)
        if(type==1)
            textView17.text = "Donor"
        if(type==2)
            textView17.text = "Distributer"
    }

    fun back(view: View){
        val intent = Intent(applicationContext, signup::class.java)
        startActivity(intent)
    }

    fun next(view: View){
//        val type = intent.getIntExtra("user", -1)

        if(type!=-1){
            setImage(type)
        }
    }

    fun setImage(type: Int){
        disappearBox()
        if(type==1){

        }
        else if(type==2){

        }
    }

    fun disappearBox(){

    }

    fun showBox(){

    }

}