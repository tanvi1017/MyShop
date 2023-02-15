package com.tanvi.myshop.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.tanvi.myshop.BaseActivity
import com.tanvi.myshop.LoginActivity
import com.tanvi.myshop.RegistrationActivity
import com.tanvi.myshop.models.User
import com.tanvi.myshop.utils.Constants

 class FirestoreClass {

     private val mFirestore = FirebaseFirestore.getInstance()
    fun registerUser(activity: RegistrationActivity,userInfo:User){
        mFirestore.collection(Constants.USERS).document(userInfo.id).set(userInfo, SetOptions.merge()).addOnSuccessListener {
           activity.userRegistrationSuccess()
        }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e( activity.javaClass.simpleName,"Error while registering the user.",e)

            }
    }
    private fun getCurrentUserID():String{
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID =""
        if (currentUser!=null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
     fun getUserDetails(activity:Activity){
         mFirestore.collection(Constants.USERS)
             .document(getCurrentUserID())
             .get()
             .addOnSuccessListener { document ->
                 Log.i(activity.javaClass.simpleName,document.toString())
                 val user= document.toObject(User::class.java)!!
                 val sharedPreferences = activity.getSharedPreferences(Constants.MYSHOP_PREFERENCES,Context.MODE_PRIVATE)
                 val editor:SharedPreferences.Editor=sharedPreferences.edit()
                 editor.putString(Constants.LOGGED_IN_USERNAME,"${user.firstName} ${user.lastName}")
                 editor.apply()

                 when(activity){
                     is LoginActivity ->{
                         activity.userLoggedInSuccess(user)
                     }
                 }
             }
             .addOnFailureListener { e ->

             }

     }
 }