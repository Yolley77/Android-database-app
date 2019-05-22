package com.yollee.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

const val DATABASE_VERSION = 2
const val DATABASE_NAME = "studentsDB"
const val TABLE_NAME = "students"

const val KEY_ID = "_id"
const val KEY_NAME = "name"
const val KEY_SURNAME = "surname"
const val KEY_MIDDLENAME = "middlename"
const val KEY_TIME = "time"

class DBHelper(context: Context?, name: String? = DATABASE_NAME, factory: SQLiteDatabase.CursorFactory? = null, version: Int = DATABASE_VERSION)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("CREATING", " --- onCreate database --- ")
        db?.execSQL("create table " + TABLE_NAME + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text,"
                + KEY_SURNAME + " text," + KEY_MIDDLENAME + " text," + KEY_TIME + " text" + ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}