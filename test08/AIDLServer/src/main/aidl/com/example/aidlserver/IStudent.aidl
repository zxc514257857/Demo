package com.example.aidlserver;
import com.example.aidlserver.bean.StudentInfo;

interface IStudent {

    void addStudentInfoReq(in StudentInfo studentInfo);
}
