package com.zhr.test.bean

import android.os.Parcel
import android.os.Parcelable

class Person1 : Parcelable {

    var name: String? = null
    var age: Int? = null

    constructor(name: String?, age: Int?) {
        this.name = name
        this.age = age
    }

    constructor(parcel: Parcel) {
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

    companion object CREATOR : Parcelable.Creator<Person1> {
        override fun createFromParcel(parcel: Parcel): Person1 {
            return Person1(parcel)
        }

        override fun newArray(size: Int): Array<Person1?> {
            return arrayOfNulls(size)
        }
    }
}