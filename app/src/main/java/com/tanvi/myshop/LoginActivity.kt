package com.tanvi.myshop

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView

class LoginActivity :AppCompatActivity(),View.OnClickListener {
    lateinit var tvRegister:TextView
    lateinit var tvForgotpassword:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        tvRegister=findViewById(R.id.tvRegister)
        tvForgotpassword=findViewById(R.id.tvForgotpassword)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.tvForgotpassword -> {
                }
                R.id.btnLogin -> {
                    validateLoginDetails()
                }
                R.id.tvRegister -> {
                    val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    private fun validateLoginDetails():Boolean{
        return when{
            TextUtils.isEmpty(etEmail.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(etPassword.text.toString().trim{ it <=' '}) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_confirm_password),
                    true
                )
                false
            }
            else ->{
                showErrorSnackBar("Your details are valid",false)
                true
            }
            }
            }
        }

