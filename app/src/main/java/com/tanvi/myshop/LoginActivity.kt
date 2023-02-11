package com.tanvi.myshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    lateinit var tvRegister:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        tvRegister=findViewById(R.id.tvRegister)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
            startActivity(intent)

        }
    }
}