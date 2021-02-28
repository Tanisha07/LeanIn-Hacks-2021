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
//import com.example.foodfightersnew.Global.Companion.uuid
import com.example.foodfightersnew.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_datails.*
import java.util.*
import kotlin.math.log

class datails : AppCompatActivity() {
    var type = -1
    // state 1-> textViews for details
    // state 2-> imageViews
    var state = 1

    // firebase var initialization
    var mAuth : FirebaseAuth?=null
    val mAuthListener: FirebaseAuth.AuthStateListener? = null
    var firebaseDatabase : FirebaseDatabase? = null
    var mRef: DatabaseReference? = null


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

        // getting firebase instance
        try {
            mAuth = FirebaseAuth.getInstance()
            firebaseDatabase = FirebaseDatabase.getInstance()
            mRef = firebaseDatabase!!.reference
        }
        catch (e : Exception){
            Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG).show()
        }
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

        if(state==2){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        if(state ==1) {
            state = 2
            addData()
        }
        if(type!=-1){
            setImage(type)
        }



    }

    fun setImage(type: Int){
        disappearBox()
        showImg()
//        addData()
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
//        Toast.makeText(applicationContext, "yoohooo", Toast.LENGTH_LONG).show()
        imageView.visibility = View.VISIBLE
        imageView2.visibility = View.VISIBLE
        imageView3.visibility = View.VISIBLE
        imageView4.visibility = View.VISIBLE
    }

    fun addData(){


        try {
//            val uuid = UUID.randomUUID()
//            val imgName = "images/$uuid.jpg"
//            val storageRef = storageReference!!.child(imgName)


//                var downloadUrl = taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
            val user = mAuth!!.currentUser
            val userEmail = user!!.email.toString()
            val userName = textView9.text.toString()
            val userPhone = textView12.text.toString()
            val userAdd = textView14.text.toString()
            val userType = type.toString()
//            val userName = textView9.text.toString()
            val uuid = UUID.randomUUID()
            Global.curr_uuid = uuid

            val uuidString = uuid.toString()




            mRef!!.child("User").child(uuidString).child("userMail").setValue(userEmail)
            mRef!!.child("User").child(uuidString).child("userName").setValue(userName)
            mRef!!.child("User").child(uuidString).child("userPhone").setValue(userPhone)
            mRef!!.child("User").child(uuidString).child("userAdd").setValue(userAdd)
            mRef!!.child("User").child(uuidString).child("userType").setValue(userType)
            Toast.makeText(applicationContext, uuidString, Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext, "Details Saved", Toast.LENGTH_LONG).show()

        }
        catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG)
        }


    }

}