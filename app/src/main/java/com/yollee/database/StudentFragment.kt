package com.yollee.database

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

@SuppressLint("ValidFragment")
class StudentFragment constructor(var _id : Int, var fullname: String, val time : String) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.retainInstance = true
        val context = activity.applicationContext
        val layout = LinearLayout(context)
        val id = TextView(context)
        val fullname = TextView(context)
        val time = TextView(context)
        id.text = this._id.toString() + ". "
        fullname.text = this.fullname + " "
        time.text = this.time + "\n"
        layout.addView(id)
        layout.addView(fullname)
        layout.addView(time)
        return layout
    }
}