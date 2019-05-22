package com.yollee.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

const val DATABASE_VERSION = 2
const val DATABASE_NAME = "studentsDB"
const val TABLE_NAME = "students"

const val KEY_ID = "_id"
const val KEY_NAME = "name"
const val KEY_SURNAME = "surname"
const val KEY_MIDDLENAME = "middlename"
const val KEY_FULLNAME = "fullname"
const val KEY_TIME = "time"

class DBHelper(context: Context?, name: String? = DATABASE_NAME, factory: SQLiteDatabase.CursorFactory? = null, version: Int = DATABASE_VERSION)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("CREATING", " --- onCreate database --- ")
        db?.execSQL("create table $TABLE_NAME (  $KEY_ID"
                + " integer primary key, $KEY_NAME text,"
                + "$KEY_SURNAME  text, $KEY_MIDDLENAME text, $KEY_TIME text)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("UPGRADING", "Upgrading database from $oldVersion to $newVersion")

        if (oldVersion == 1 && newVersion == 2) {
            val cv = ContentValues()

            db?.beginTransaction()
            try {
                db?.execSQL("create temporary table ${TABLE_NAME}_tmp ("
                + "$KEY_ID integer, $KEY_FULLNAME text, $KEY_TIME text);"
                )
                db?.execSQL("insert into ${TABLE_NAME}_tmp select $KEY_ID, $KEY_FULLNAME, $KEY_TIME from $TABLE_NAME;")
                db?.execSQL("drop table $TABLE_NAME;")
                db?.execSQL("create table $TABLE_NAME (  $KEY_ID"
                        + " integer primary key, $KEY_NAME text,"
                        + "$KEY_SURNAME  text, $KEY_MIDDLENAME text, $KEY_TIME text)"
                )

                val cursor = db?.query(TABLE_NAME + "_tmp", null, null, null, null, null, null)
                val fullnames : ArrayList<String> = ArrayList()
                if(cursor!!.moveToFirst()) {
                    val fullnameInd = cursor.getColumnIndex(KEY_FULLNAME)
                    do {
                        fullnames.add(cursor.getString(fullnameInd))
                    } while (cursor.moveToNext())
                }
                cursor.close()
                val splittedNames : ArrayList<List<String>> = ArrayList()
                for (i in 0 until fullnames.size) {
                    splittedNames.add(fullnames[i].split(" "))
                }

                db?.execSQL("create temporary table temp (  $KEY_ID"
                        + " integer primary key, $KEY_NAME text,"
                        + "$KEY_SURNAME  text, $KEY_MIDDLENAME text)"
                )

                for (i in 0 until fullnames.size) {
                    cv.put(KEY_NAME, splittedNames[i][0])
                    cv.put(KEY_SURNAME, splittedNames[i][2])
                    cv.put(KEY_MIDDLENAME, splittedNames[i][1])
                    db.insert("temp", null, cv)
                }


                /*db?.execSQL("insert into $TABLE_NAME(name, surname, middlename, time) select  $KEY_NAME, $KEY_SURNAME, " +
                        "$KEY_MIDDLENAME, $KEY_TIME from ${TABLE_NAME}_tmp, temp")*/
                db?.execSQL("insert into $TABLE_NAME(name, surname, middlename, time) select  $KEY_NAME, $KEY_SURNAME, " +
                        "$KEY_MIDDLENAME from temp union" +
                        "sselect $KEY_TIME from ${TABLE_NAME}_tmp")
                db?.execSQL("drop table ${TABLE_NAME}_tmp")
                db?.execSQL("drop table" + " temp;")

                db.setTransactionSuccessful()
            } finally {
                db?.endTransaction()
            }
        }
    }
}