package com.tanvi.myshop

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Log.e
import android.util.Log.i
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.tanvi.myshop.models.User
import com.tanvi.myshop.utils.Constants

class LoginActivity : BaseActivity(),View.OnClickListener {
    lateinit var tvRegister:TextView
    lateinit var tvForgotpassword:TextView
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        tvRegister = findViewById(R.id.tvRegister)
        tvForgotpassword = findViewById(R.id.tvForgotpassword)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

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
        }
    }
        fun userLoggedInSuccess(user: User){
            hideProgressDialog()
            Log.i("First Name",user.firstName)
            Log.i("LastName",user.lastName)
            Log.i("Email",user.email)
            if (user.profileCompleted==0){
                val intent=Intent(this@LoginActivity,UserProfileActivity::class.java)
                intent.putExtra(Constants.EXTRA_USER_DETAILS,user)
                startActivity(intent)
            }
            else {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
            finish()
        }

     override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.tvForgotpassword -> {

                }
                R.id.btnLogin -> {
                   logInRegisteredUser()
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
                true }
            }
            }
     private fun logInRegisteredUser(){
         if (validateLoginDetails()){
             showProgressDialog(resources.getString(R.string.please_wait))
             val email =etEmail.text.toString().trim(){it <= ' '}
             val password=etPassword.text.toString().trim { it <=' ' }
             FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                 .addOnCompleteListener { task ->
                     hideProgressDialog()
                     if (task.isSuccessful){
                         showErrorSnackBar("You are logged in successfully.",false)
                     }
                     else{
                         hideProgressDialog()
                         showErrorSnackBar(task.exception!!.message.toString(),true)
                     }
             }
         }
     }

        }

