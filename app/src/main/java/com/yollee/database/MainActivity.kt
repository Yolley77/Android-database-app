package com.yollee.database

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.support.annotation.RequiresApi
import java.time.LocalDateTime
import android.util.Log


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var dbHelper : DBHelper
    lateinit var db : SQLiteDatabase
    var cv : ContentValues = ContentValues()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        db = dbHelper.writableDatabase
        db.delete(TABLE_NAME, null, null)
        for (i in 0..4) {
            setRandomStudent()
        }

        seeButton.setOnClickListener(this)
        addButton.setOnClickListener(this)
        changeButton.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v : View) {
        when(v.id) {
            R.id.seeButton ->{
                val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
                val data : ArrayList<String> = ArrayList()
                val students : ArrayList<Student> = ArrayList()
                if(cursor.moveToFirst()) {
                    val idInd = cursor.getColumnIndex(KEY_ID)
                    val fullnameInd = cursor.getColumnIndex(KEY_FULLNAME)
                    val timeInd = cursor.getColumnIndex(KEY_TIME)
                    do {
                        students.add(Student(cursor.getInt(idInd), cursor.getString(fullnameInd), cursor.getString(timeInd)))
                        data.add(cursor.getInt(idInd).toString() + " : " + cursor.getString(fullnameInd) + " : " + cursor.getString(timeInd) + "\n")
                    } while (cursor.moveToNext())
                } else {
                    data.add("NOTHING")
                }
                cursor.close()

                val i = Intent(baseContext, StudentList::class.java)
                i.putStringArrayListExtra("data", data)
                i.putParcelableArrayListExtra("students", students)
                startActivity(i)
            }
            R.id.addButton -> {
                // Метод, добавляющий рандомного поцика
                setRandomStudent()
            }
            R.id.changeButton -> {
                // Метод, меняющий последнего поцика на ваню
                cv.put(KEY_FULLNAME, "Иванов Иван Иванович")
                cv.put(KEY_TIME, LocalDateTime.now().toString())

                val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
                val cnt = cursor.count
                cursor.close()

                Log.wtf("CNT IS - ", "$cnt")
                db.update(TABLE_NAME, cv, "$KEY_ID = ?", arrayOf("$cnt"))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setRandomStudent() {
        val s = Student()
        s.setRandomStudent()
        cv.put(KEY_FULLNAME, s.fullName)
        cv.put(KEY_TIME, s.time)
        db.insert(TABLE_NAME, null, cv)
    }
}
