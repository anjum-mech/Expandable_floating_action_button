package com.malakinfo.lagn

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var mBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.app_name)


        mBtn = findViewById(R.id.mChagenLagn)

        mBtn.setOnClickListener {
            showChanLagn().javaClass

        }
    }

        private fun showChanLagn() {

            val listItems = arrayOf("हिन्दी", "मराठी", "English")
            val mBuilder = AlertDialog.Builder(this@MainActivity)
            mBuilder.setTitle("Choose Language")
            mBuilder.setSingleChoiceItems(listItems, -1,DialogInterface.OnClickListener(){
                 dialog, which ->

                    if (which == 0) {
                        setAppLocale("hi")
                        recreate()
                    } else if (which == 1) {
                        setAppLocale("mr")
                        recreate()
                    } else if (which == 2) {
                        setAppLocale("en")
                        recreate()
                    }
                    dialog.dismiss()

            })
            val mDialog = mBuilder.create()

            mDialog.show()

        }

    private fun setAppLocale(localeCode: String) {
        val resources = resources
        val dm = resources.displayMetrics
        val config = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(Locale(localeCode.toLowerCase()))
        } else {
            config.locale = Locale(localeCode.toLowerCase())
        }
        resources.updateConfiguration(config, dm)

    }

       /* private fun loadLocate() {
            val pref = getSharedPreferences("Setting",Activity.MODE_PRIVATE)
            var leng = pref.getString("My_Lagn","")
            setAppLocale(localClassName)


        }*/

}
