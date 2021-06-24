package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.example.aidlserver.IStudent;
import com.example.aidlserver.bean.StudentInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn;
    private IStudent mIStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = findViewById(R.id.btn);

        Intent intent = new Intent();
        // Server端AndroidManifest中配置的action和package
        intent.setAction("com.example.aidlserver.studentserver");
        intent.setPackage("com.example.aidlserver");
        bindService(intent, serverConn, Context.BIND_AUTO_CREATE);

        mBtn.setOnClickListener(this);
    }

    private ServiceConnection serverConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            mIStudent = IStudent.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            unbindService(serverConn);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                StudentInfo studentInfo = new StudentInfo("张宏睿", 27, 85);
                try {
                    mIStudent.addStudentInfoReq(studentInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }
}
