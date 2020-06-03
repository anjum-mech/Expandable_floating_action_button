package com.malakinfo.tailoraapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.malakinfo.tailoraapp.Constants.CREATE_TABLE

class MyDbHelper(context: Context):SQLiteOpenHelper(
    context,Constants.DB_NAME,
    null,
    Constants.DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) = db.execSQL(Constants.TABLE_NAME)

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS"+Constants.TABLE_NAME)
        onCreate(db)
    }
    fun insertRecord(
        name:String,
        image:String?,
        bio:String?,
        phone:String?,
        dobEnter:String?,
        dobReliga : String,
        addedTime:String?,
        updatedTime:String?
    ) : Long
    {
       val db = this.writableDatabase
        val values = ContentValues()
        values.put(Constants.C_NAME,name)
        values.put(Constants.C_IMAGE,image)
        values.put(Constants.C_BIO,bio)
        values.put(Constants.C_PHONE,phone)
        values.put(Constants.C_DOBENTER,dobEnter)
        values.put(Constants.C_DOBRELIGAJ,dobReliga)
        values.put(Constants.C_ADDED_TIMESTAMP,addedTime)
        values.put(Constants.C_UPDATED_TIMESTAMP,updatedTime)

        val id = db.insert(Constants.TABLE_NAME,null,values)
        return id
    }
    fun getAllRecords(orderBy:String) : ArrayList<ModelRecord>{

        val recordList = ArrayList<ModelRecord>()
        val selectQuery = "SELECT + FROM ${Constants.TABLE_NAME}ORDER BY $orderBy"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)
        if (cursor.moveToFirst()){
            do {
                val modelRecord = ModelRecord(
                    ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_BIO)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_PHONE)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_DOBENTER)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_DOBRELIGAJ)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP))
                )
                recordList.add(modelRecord)
            }while (cursor.moveToNext())
        }
        db.close()
        return recordList

    }
    fun searchRecords(query:String):ArrayList<ModelRecord>{
        val recordList = ArrayList<ModelRecord>()
        val selectQuery = "SELECT + FROM ${Constants.TABLE_NAME}WHERE  ${Constants.C_NAME}LIKE '% $query %'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)
        if (cursor.moveToFirst()){
            do {
                val modelRecord = ModelRecord(
                    ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_BIO)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_PHONE)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_DOBENTER)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_DOBRELIGAJ)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP))
                )
                recordList.add(modelRecord)
            }while (cursor.moveToNext())
        }
        db.close()
        return recordList

    }

}