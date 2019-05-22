package com.yollee.database

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.RequiresApi
import java.time.LocalDateTime

class Student(var id : Int = 0, var name : String = "", var surname : String = "",
              var middlename : String = "", var time : String = "") : Parcelable {

    val names : Array<String> = arrayOf(
        "Владимир", "Иосиф", "Георгий", "Никита", "Леонид", "Юрий", "Константин", "Михаил",
        "Барак", "Дональд", "Билл", "Джимми", "Рональд", "Джордж", "Борис"
    )

    val surnames : Array<String> = arrayOf(
        "Ленин", "Сталин", "Маленков", "Хрущёв", "Брежнев", "Андропов", "Черненко", "Горбачёв", "Путин",
        "Картер", "Рейган", "Форд", "Никсон", "Ельцин", "Кеннеди", "Джонсон"
    )

    val middlenames : Array<String> = arrayOf(
        "Ильич", "Виссарионович", "Максимилианович", "Сергеевич", "Владимирович", "Устинович", ""
    )

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(middlename)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setRandomStudent() {
        name = names[(Math.random() * 100).toInt()%15]
        surname = surnames[(Math.random() * 100).toInt()%16]
        middlename = middlenames[(Math.random() * 10).toInt()%7]
        time = LocalDateTime.now().toString()
    }
}