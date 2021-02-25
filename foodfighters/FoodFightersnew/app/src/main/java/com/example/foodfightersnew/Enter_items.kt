package com.example.foodfightersnew

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_enter_items.*


class Enter_items : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_items)
    }

    fun done(view: View){
        val name = editTextTextPersonName.text.toString()
        val qty = editTextTextPersonName2.text.toString()
        var id: Int = radioGroup.checkedRadioButtonId
        if ((name!="" && name.trim().isNotEmpty()) && (qty!="" && qty.trim().isNotEmpty()) && id!=-1){
            val qty_num = qty.toInt()
            Global.item_name.add(name)
            Global.item_qty.add(qty_num)
            Global.item_type.add(id)

            val returnIntent = Intent()
            returnIntent.putExtra("result", "result")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
        else{
            Toast.makeText(applicationContext, "Please enter all the details", Toast.LENGTH_LONG).show()
        }
    }
}