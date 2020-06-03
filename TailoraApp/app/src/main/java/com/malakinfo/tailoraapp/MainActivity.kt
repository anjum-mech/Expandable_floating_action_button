package com.malakinfo.tailoraapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var dbHelper : MyDbHelper
    private val NEWEST_FIRST = "${Constants.C_ADDED_TIMESTAMP}DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attRecycel()



        addRecordBtn.setOnClickListener {
            cilkNextActi()
        }
    }
    fun attRecycel(){

        dbHelper = MyDbHelper(this)
        loadRecords()
    }

    private fun loadRecords() {
        val adapterRecord = taiAdapter(this,dbHelper.getAllRecords(NEWEST_FIRST))
        recordRv.adapter = adapterRecord

    }

    fun cilkNextActi()
    {
        startActivity(Intent(this,AddUpdateRecordActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        loadRecords()
    }

}
