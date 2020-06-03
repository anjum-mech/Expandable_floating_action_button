package com.malakinfo.buttondesing

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_click.setOnClickListener {

            btn_click.setBackgroundResource(R.drawable.btn_custom)
            Toast.makeText(this,"This is Button was clicked",Toast.LENGTH_SHORT).show()
        }
    }
}
