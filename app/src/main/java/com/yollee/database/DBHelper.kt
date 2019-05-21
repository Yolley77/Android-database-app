package com.yollee.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "studentsDB"
const val TABLE_NAME = "students"

const val KEY_ID = "_id"
const val KEY_FULLNAME = "fullName"
const val KEY_TIME = "time"

class DBHelper(context: Context?, name: String? = DATABASE_NAME, factory: SQLiteDatabase.CursorFactory? = null, version: Int = DATABASE_VERSION)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table " + TABLE_NAME + "(" + KEY_ID
                + " integer primary key," + KEY_FULLNAME + " text," + KEY_TIME + " text" + ")"
        )

        //db?.delete(TABLE_NAME, null, null)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}