package com.tanvi.myshop

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    private lateinit var mProgressDialog:Dialog

     fun showErrorSnackBar(message: String,errorMessage: Boolean){
         val snackBar=Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
         val snackBarView=snackBar.view
         if (errorMessage){
             snackBarView.setBackgroundColor(ContextCompat.getColor(this@BaseActivity,R.color.colorSnackBarError))
         }else{
             snackBarView.setBackgroundColor(ContextCompat.getColor(this@BaseActivity,R.color.colorSnackBarError))
         }
         snackBar.show()
     }
    fun showProgressDialog(text:String){
        mProgressDialog = Dialog(this)
        mProgressDialog.setContentView(R.layout.activity_progress_dialog)
        mProgressDialog.tv_progress_text.text =text
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()
    }
    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }
}