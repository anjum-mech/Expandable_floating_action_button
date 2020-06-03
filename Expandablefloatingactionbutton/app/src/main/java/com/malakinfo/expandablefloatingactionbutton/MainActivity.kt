package com.malakinfo.expandablefloatingactionbutton

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isopen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabopen = AnimationUtils.loadAnimation(this,R.anim.fab_open)
        val fabclose  = AnimationUtils.loadAnimation(this,R.anim.fab_close)
        val fabclockwise = AnimationUtils.loadAnimation(this,R.anim.fab_clockwise)
        val fabanticlockwise = AnimationUtils.loadAnimation(this,R.anim.fab_anticlockwise)

        btnFab.setOnClickListener {
            if (isopen){
                btnedit.startAnimation(fabclose)
                btnAlaram.startAnimation(fabclose)
                btnFab.startAnimation(fabclockwise)

                isopen = false
            }else{
                btnedit.startAnimation(fabopen)
                btnAlaram.startAnimation(fabopen)
                btnFab.startAnimation(fabanticlockwise)

                btnedit.isClickable
                btnAlaram.isClickable

                isopen = true
            }

            btnedit.setOnClickListener {
                Toast.makeText(this,"you are clicked edit button",Toast.LENGTH_SHORT).show()

            }

            btnAlaram.setOnClickListener {
                Toast.makeText(this,"you are clicked alaram button",Toast.LENGTH_SHORT).show()
            }
        }






    }
}