package com.malakinfo.backbuttonclick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBack.setOnClickListener {
            val bIntent = Intent(this,NewActivity::class.java)
            startActivity(bIntent)
            Toast.makeText(this,"Open A New Activity",Toast.LENGTH_SHORT).show()
        }

    }
}
