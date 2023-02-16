package com.tanvi.myshop.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import java.util.jar.Attributes

 class MSPRadioButton(context:Context,attributes: AttributeSet):AppCompatRadioButton(context,attributes) {
    init {
        applyFont()
    }
    private fun applyFont(){
        val typeface:Typeface= Typeface.createFromAsset(context.assets,"Montserrat-Bold.ttf")
        setTypeface(typeface)
    }
}