package com.malakinfo.tailoraapp

object Constants {
    const val DB_NAME = "MY_RECORDS_DB"
    const val DB_VERSION = 1
    const val TABLE_NAME = "MY_RECORDS_TABLE"
    const val C_ID = "ID"
    const val C_NAME = "NAME"
    const val C_IMAGE = "IMAGE"
    const val C_BIO = "BIO"
    const val C_PHONE = "PHONE"
    const val C_DOBENTER = "DOBENTER"
    const val C_DOBRELIGAJ = "DOBRELIGAJ"
    const val C_ADDED_TIMESTAMP = "ADDED_TIME_STAMP"
    const val C_UPDATED_TIMESTAMP = "UPDATED_TIMESTAMP"


    const val CREATE_TABLE = (
            "CREATE TABLE " + TABLE_NAME + "("
            + C_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + C_NAME + "TEXT,"
                    + C_IMAGE + "TEXT,"
                    + C_BIO + "TEXT,"
                    + C_PHONE + "TEXT,"
                    + C_DOBENTER + "TEXT,"
                    + C_DOBRELIGAJ + "TEXT,"
                    + C_ADDED_TIMESTAMP + "TEXT,"
                    + C_UPDATED_TIMESTAMP + "TEXT,"
            + ")"
            )



}