package com.tanvi.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : BaseActivity() {
    lateinit var tvRegistration:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvRegistration=findViewById(R.id.tvRegister)
        setContentView(R.layout.activity_forgot_password)
        setupActionBar()
        tvRegistration.setOnClickListener {
        }
    }
    private fun setupActionBar() {
        setSupportActionBar(toolbar_activity_forgot_password)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_ios_24)
        }
        toolbar_activity_forgot_password.setNavigationOnClickListener { onBackPressed() }
    }

}




