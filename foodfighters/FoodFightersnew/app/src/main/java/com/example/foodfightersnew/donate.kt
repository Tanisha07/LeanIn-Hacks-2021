package com.example.foodfightersnew

import androidx.appcompat.widget.Toolbar;
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
//import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_donate.*

class donate : AppCompatActivity() {
    var arrayAdapter: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_donate)

//        var toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        val actionBar: ActionBar? = supportActionBar
//
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,  Global.item_name)
        listView.adapter = arrayAdapter
    }

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


}