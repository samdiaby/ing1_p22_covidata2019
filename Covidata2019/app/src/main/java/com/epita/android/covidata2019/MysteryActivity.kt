package com.epita.android.covidata2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_mystery.*

class MysteryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mystery)

        //HSV.postDelayed( {HSV.fullScroll(HorizontalScrollView.FOCUS_RIGHT)},10000L)
        HSV.post { HSV.smoothScrollTo(0,HSV.right)}

    }
}