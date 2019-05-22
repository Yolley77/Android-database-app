package com.yollee.database

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import kotlinx.android.synthetic.main.activity_student_list.*

class StudentList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        //studentsView.text = intent.getStringArrayListExtra("data").toString()
        val students = intent.getParcelableArrayListExtra<Student>("students")
        for (i in 0 until students.size) {
            Log.i("ELEMENT ${i+1}", students[i].fullName
                    + " " + (students[i].time))
        }

        if(savedInstanceState == null) {
            // получаем экземпляр FragmentTransaction
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager
                .beginTransaction()

            for (i in 0 until students.size) {
                val fragment = StudentFragment(students[i].fullName, students[i].time)
                fragmentTransaction.add(R.id.container, fragment)

            }
            fragmentTransaction.commit()
        }
    }
}
