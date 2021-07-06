package com.wzy.installdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class InstallBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent!=null){
            String action = intent.getAction();
            String msg = intent.getStringExtra("msg");
            Log.e("InstallBroadCast",action +" -- "+ msg);
        }

    }
}
