package com.zhr.test.bean

import android.os.Parcel
import android.os.Parcelable

class Person2() : Parcelable {

    var name: String? = null
    var age: Int? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        age = parcel.readValue(Int::class.java.classLoader) as? Int
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person2> {
        override fun createFromParcel(parcel: Parcel): Person2 {
            return Person2(parcel)
        }

        override fun newArray(size: Int): Array<Person2?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Person2(name=$name, age=$age)"
    }
}