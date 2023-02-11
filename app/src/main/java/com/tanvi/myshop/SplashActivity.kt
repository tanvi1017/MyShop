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
    lateinit var tvMyShop:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        tvMyShop=findViewById(R.id.tvMyShop)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
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
            val i = Intent(this@SplashActivity,LoginActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)
        val typeface:Typeface =Typeface.createFromAsset(assets,"Kastore-Bold.otf")
        tvMyShop.typeface =typeface
    }
    }
