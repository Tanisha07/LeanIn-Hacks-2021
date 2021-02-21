package com.example.foodfightersnew

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class datails : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_datails)
//    }
//}

//package com.tani.foodfighters
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_datails.*
import kotlin.math.log

class datails : AppCompatActivity() {
    var type = -1
    // state 1-> textViews for details
    // state 2-> imageViews
    var state = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datails)

        // make img views disappear
        disappearImg()

        // get type of user from intent(donor/ dist)
        type = intent.getIntExtra("user", -1)
        if(type==1)
            textView17.text = "Donor"
        if(type==2)
            textView17.text = "Distributer"
    }

    // go to prev Act
    fun back(view: View){
        //if textbox, go back to prev act
        if(state==1){
            val intent = Intent(applicationContext, signup::class.java)
            startActivity(intent)
        }

        //if img show textviews again
        if(state==2){
            disappearImg()
            showBox()
            state = 1
        }
    }

    // after text boxes
    fun next(view: View){
//        val type = intent.getIntExtra("user", -1)
        if(state ==1)
            state = 2
        if(type!=-1){
            setImage(type)
        }
    }

    fun setImage(type: Int){
        disappearBox()
        showImg()
        if(type==1){
            imageView.setImageResource(R.drawable.ic_launcher_background)
            imageView2.setImageResource(R.drawable.ic_launcher_background)
            imageView3.setImageResource(R.drawable.ic_launcher_background)
            imageView4.setImageResource(R.drawable.ic_launcher_background)
        }
        else if(type==2){
            imageView.setImageResource(R.drawable.ic_launcher_foreground)
            imageView2.setImageResource(R.drawable.ic_launcher_foreground)
            imageView3.setImageResource(R.drawable.ic_launcher_foreground)
            imageView4.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    fun disappearBox(){
        textView8.visibility = View.INVISIBLE
        textView9.visibility = View.INVISIBLE
        textView10.visibility = View.INVISIBLE
        textView11.visibility = View.INVISIBLE
        textView12.visibility = View.INVISIBLE
        textView13.visibility = View.INVISIBLE
        textView14.visibility = View.INVISIBLE
        textView15.visibility = View.INVISIBLE
    }

    fun showBox(){
        textView8.visibility = View.VISIBLE
        textView9.visibility = View.VISIBLE
        textView10.visibility = View.VISIBLE
        textView11.visibility = View.VISIBLE
        textView12.visibility = View.VISIBLE
        textView13.visibility = View.VISIBLE
        textView14.visibility = View.VISIBLE
        textView15.visibility = View.VISIBLE

        textView9.setText("")
        textView10.setText("")
        textView14.setText("")
        textView12.setText("")
    }

    fun disappearImg(){
        imageView.visibility = View.INVISIBLE
        imageView2.visibility = View.INVISIBLE
        imageView3.visibility = View.INVISIBLE
        imageView4.visibility = View.INVISIBLE
    }

    fun showImg(){
        Toast.makeText(applicationContext, "yoohooo", Toast.LENGTH_LONG).show()
        imageView.visibility = View.VISIBLE
        imageView2.visibility = View.VISIBLE
        imageView3.visibility = View.VISIBLE
        imageView4.visibility = View.VISIBLE
    }

}