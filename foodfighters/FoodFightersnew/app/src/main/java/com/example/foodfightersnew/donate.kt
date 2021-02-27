package com.example.foodfightersnew

import androidx.appcompat.widget.Toolbar;
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
//import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_datails.*
import kotlinx.android.synthetic.main.activity_donate.*
import java.util.*
import java.util.jar.Manifest

class donate : AppCompatActivity() {

    var arrayAdapter: ArrayAdapter<String>? = null
    var mAuth : FirebaseAuth?=null
    val mAuthListener: FirebaseAuth.AuthStateListener? = null
    var firebaseDatabase : FirebaseDatabase? = null
    var mRef: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_donate)

//        var toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        val actionBar: ActionBar? = supportActionBar

//        Global.item_name.add("apple")
//        Global.item_name.add("roti")
//
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, Global.item_name)
        listView.adapter = arrayAdapter

        try {
            mAuth = FirebaseAuth.getInstance()
            firebaseDatabase = FirebaseDatabase.getInstance()
            mRef = firebaseDatabase!!.reference
        }
        catch (e : Exception){
            Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_item){
            val intent = Intent(applicationContext, Enter_items::class.java)
//        startActivityForResult(intent, 1);
            startActivityForResult(intent, 1)
        }
        return super.onOptionsItemSelected(item)
    }


    // location request

    fun addItem(view: View){
        val intent = Intent(applicationContext, Enter_items::class.java)
//        startActivityForResult(intent, 1);
        startActivityForResult(intent, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode==1 && resultCode==Activity.RESULT_OK && data!=null){
//            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_2, Global.item_name)
//            arrayAdapter.notifyDataSetChanged()
            if (Global.item_name.size>0)
            Toast.makeText(applicationContext, Global.item_name[0], Toast.LENGTH_LONG).show()
            arrayAdapter!!.notifyDataSetChanged()
//            var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, R.id.textView23, Global.item_name)
//            listView.adapter = arrayAdapter
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    fun next(view: View){

        try {
//            val uuid = UUID.randomUUID()
            val uuidString = Global.curr_uuid.toString()

//            val uuidString = uuid.toString()

            mRef!!.child("User").child(uuidString).child("food").setValue(Global.item_name)
            mRef!!.child("User").child(uuidString).child("qty").setValue(Global.item_qty)
            mRef!!.child("User").child(uuidString).child("food_type").setValue(Global.item_type)
            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()

        }
        catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(applicationContext, "error 4535", Toast.LENGTH_LONG)
        }

        var intent = Intent(applicationContext, MapsActivity::class.java)
        startActivity(intent)
    }
}