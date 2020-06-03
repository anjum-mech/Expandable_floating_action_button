package com.malakinfo.buttonclick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClick.setOnClickListener {
            val bIntent = Intent(this,NextActivity::class.java)
            startActivity(bIntent)
            Toast.makeText(this,"This Button is Click",Toast.LENGTH_SHORT).show()
        }
    }
}
