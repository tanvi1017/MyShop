package com.tanvi.myshop

import android.content.Context.MODE_PRIVATE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.core.Context
import com.tanvi.myshop.utils.Constants

class MainActivity : AppCompatActivity() {
    lateinit var tv_main:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        tv_main=findViewById(R.id.tv_main)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences(Constants.MYSHOP_PREFERENCES,Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.LOGGED_IN_USERNAME,"")!!
        tv_main.text="Hello $username"
    }
}