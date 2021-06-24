package com.example.aidlserver.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentInfo implements Parcelable {

    private String name;
    private int mathScore;
    private int englishScore;

    public StudentInfo(String name, int mathScore, int englishScore) {
        this.name = name;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }

    protected StudentInfo(Parcel in) {
        name = in.readString();
        mathScore = in.readInt();
        englishScore = in.readInt();
    }

    public static final Creator<StudentInfo> CREATOR = new Creator<StudentInfo>() {
        @Override
        public StudentInfo createFromParcel(Parcel in) {
            return new StudentInfo(in);
        }

        @Override
        public StudentInfo[] newArray(int size) {
            return new StudentInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(int englishScore) {
        this.englishScore = englishScore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(mathScore);
        dest.writeInt(englishScore);
    }
}
