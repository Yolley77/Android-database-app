package com.yollee.database

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        studentsView.text = intent.getStringArrayListExtra("data").toString()
        for (i in 0 until intent.getParcelableArrayListExtra<Student>("students").size) {
            Log.i("ELEMENT ${i+1}", (intent.getParcelableArrayListExtra<Student>("students"))[i].fullName
                    + " " + (intent.getParcelableArrayListExtra<Student>("students"))[i].time)
        }
    }
    // В идеале бахнуть ресайклер
}
