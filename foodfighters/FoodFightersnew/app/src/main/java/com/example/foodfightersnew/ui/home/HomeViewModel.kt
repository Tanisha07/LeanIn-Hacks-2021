package com.example.foodfightersnew.ui.home

import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodfightersnew.MainActivity

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Make a food donation"
    }
    val text: LiveData<String> = _text


}