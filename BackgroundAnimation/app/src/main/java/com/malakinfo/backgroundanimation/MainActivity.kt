package com.malakinfo.backgroundanimation

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAni.setOnClickListener {

            val lL = findViewById<LinearLayout>(R.id.layout)
            val ani = lL.background as AnimationDrawable
            ani.setEnterFadeDuration(2000)
            ani.setExitFadeDuration(4000)
            ani.start()
        }
    }
}
