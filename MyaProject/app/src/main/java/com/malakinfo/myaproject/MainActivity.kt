package com.malakinfo.myaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun next_going(view:View)
    {
        var name_input = etInput.text
        var bIntent = Intent(this,InputView::class.java)
        bIntent.type = name_input.toString()
        
    }
}