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
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    var mAuth : FirebaseAuth? =null
    var mAuthLister : FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mAuth = FirebaseAuth.getInstance()
        mAuthLister = FirebaseAuth.AuthStateListener {  }
    }

//    fun start(view: View){
//        var intent = Intent(applicationContext, MainActivity::class.java)
//        startActivity(intent)
//    }

    fun start (view : View){
        mAuth!!.signInWithEmailAndPassword(edituser.text.toString(), editpass.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener { exception ->
                if (exception!=null){
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

//    fun signUp(view: View){
//        var intent = Intent(applicationContext, signup::class.java)
//        startActivity(intent)
//    }

    fun signUp (view: View){
        mAuth!!.createUserWithEmailAndPassword(edituser.text.toString(), editpass.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(applicationContext,"User Created", Toast.LENGTH_LONG).show()
                    var intent = Intent(applicationContext, signup::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener { exception ->
                if (exception!=null){
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }
    }




}