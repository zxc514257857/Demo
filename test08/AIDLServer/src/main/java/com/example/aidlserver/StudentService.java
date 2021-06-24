package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.aidlserver.bean.StudentInfo;

/**
 * Error while executing process .... aidl.exe with arguments
 * 检查AIDL 文件的地方 是import没写对还是package没写对 或者是传参的参数名没写对
 */
public class StudentService extends Service {

    private String TAG = "StudentService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private IStudent.Stub stub = new IStudent.Stub(){
        @Override
        public void addStudentInfoReq(StudentInfo studentInfo){
            Log.e(TAG, "姓名：" + studentInfo.getName()
                    + " 数学成绩：" + studentInfo.getMathScore()
                    + " 英语成绩：" + studentInfo.getEnglishScore());
        }
    };
}
