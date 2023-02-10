package com.tanvi.myshop

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    lateinit var tv_app_name:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tv_app_name=findViewById(R.id.tv_app_name)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val i = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)
        val typeface:Typeface =Typeface.createFromAsset(assets,"Kastore-Bold.otf")
        tv_app_name.typeface =typeface
    }
    }
