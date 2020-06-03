package com.malakinfo.backgrounddesign

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       /* val linearLayout = findViewById<LinearLayout>(R.id.layout)
        val animationDrawable = linearLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()*/

        banChangeColor.setOnClickListener {

            val linearLayout = findViewById<LinearLayout>(R.id.layout)
            val animationDrawable = linearLayout.background as AnimationDrawable
            animationDrawable.setEnterFadeDuration(2000)
            animationDrawable.setExitFadeDuration(4000)
            animationDrawable.start()

        }


    }
}

