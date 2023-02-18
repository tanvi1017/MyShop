package com.tanvi.myshop.utils

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

object Constants {
    const val MYSHOP_PREFERENCES: String ="MyShopPrefs"
    const val USERS:String ="users"
    const val LOGGED_IN_USERNAME:String ="logged_in_username"
    const val EXTRA_USER_DETAILS:String="extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE=2
    const val PICK_IMAGE_REQUEST_CODE = 1

    fun showImageChooser(activity: Activity){
        val galleryIntent =Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    }

}