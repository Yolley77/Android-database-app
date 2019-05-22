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
class StudentFragment constructor(var _id : Int, var name: String,
                                  var surname: String, var middlename: String, val time : String) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.retainInstance = true
        val context = activity.applicationContext
        val layout = LinearLayout(context)

        val id = TextView(context)
        val fullname = TextView(context)
        val time = TextView(context)

        val idText = this._id.toString() + ". "
        val fullnameText = this.name + " " + this.surname + " " + this.middlename + " "
        val timeText = this.time + "\n"

        id.text = idText
        fullname.text = fullnameText
        time.text = timeText
        layout.addView(id)
        layout.addView(fullname)
        layout.addView(time)
        return layout
    }
}