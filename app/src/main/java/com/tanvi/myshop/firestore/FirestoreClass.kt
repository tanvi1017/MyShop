package com.tanvi.myshop.firestore

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.tanvi.myshop.BaseActivity
import com.tanvi.myshop.RegistrationActivity
import com.tanvi.myshop.models.User

class FirestoreClass {

     private val mFirestore = FirebaseFirestore.getInstance()
    fun registerUser(activity: RegistrationActivity,userInfo:User){
        mFirestore.collection("users").document(userInfo.id).set(userInfo, SetOptions.merge()).addOnSuccessListener {
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
 }