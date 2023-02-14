package com.tanvi.myshop

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class RegistrationActivity : BaseActivity() {
    lateinit var Login: TextView
    lateinit var backImage: ImageView
    lateinit var etfirstName:EditText
    lateinit var etlastName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword:EditText
    lateinit var etConfirmPassword:EditText
    lateinit var frame2:FrameLayout
     lateinit var auth: FirebaseAuth
    lateinit var cb_terms_and_conditions: AppCompatCheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        Login = findViewById(R.id.tv_Login)
        backImage = findViewById(R.id.backImage)
        etfirstName = findViewById(R.id.etFirstName)
        etlastName = findViewById(R.id.etlastName)
        etEmail = findViewById(R.id.etEmail)
        etPassword =findViewById(R.id.etPassword)
        frame2 =findViewById(R.id.frame2)
        cb_terms_and_conditions=findViewById(R.id.cb_terms_and_conditions)
        etConfirmPassword=findViewById(R.id.etConfirmPassword)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        Login.setOnClickListener {
            val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        backImage.setOnClickListener {
       onBackPressed()
        }
        frame2.setOnClickListener {
            validateRegisterDetails()
        }
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(etfirstName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }
            TextUtils.isEmpty(etlastName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }
            TextUtils.isEmpty(etEmail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(etPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(etConfirmPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
           etPassword.text.toString().trim{it <= ' '}!=etConfirmPassword.text.toString().trim { it<=' ' }->{
               showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch),true)
           false
            }
            ! cb_terms_and_conditions.isChecked ->{
                showErrorSnackBar(resources.getString(R.string.err_msg_agree_terms_and_condition),true)
                false
            }
            else -> {
                showErrorSnackBar("your details are valid",false)
                true
            }
        }
    }
    private fun registerUser(){
        if (validateRegisterDetails()){
            showProgressDialog(resources.getString(R.string.please_wait))
            val email:String = etEmail.text.toString().trim { it <= ' ' }
            val password:String=etPassword.text.toString().trim { it <= ' ' }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                onCompleteListener <AuthResult> { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        showErrorSnackBar(
                            "you are registered successfully.your user id is ${firebaseUser.uid}",
                            false
                        )
                        FirebaseAuth.getInstance().signOut();
                        finish()
                    } else {
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }

                })
        }
    }
 }