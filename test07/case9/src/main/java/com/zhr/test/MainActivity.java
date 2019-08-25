package com.zhr.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 案例九：SoundPool的使用
 * SoundPool.load ---> SoundPool.loadCompleteListener ---> Soundpool.play
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean isPlay = false;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;
    private Button mBtn7;
    private Button mBtn8;
    private Button mBtn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);
        mBtn8 = findViewById(R.id.btn8);
        mBtn9 = findViewById(R.id.btn9);

        SoundPoolUtils.initSound(this);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
        mBtn6.setOnClickListener(this);
        mBtn7.setOnClickListener(this);
        mBtn8.setOnClickListener(this);
        mBtn9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn1:
                SoundPoolUtils.playSound(99);
                break;

            case R.id.btn2:
                SoundPoolUtils.playSound(1);
                break;

            case R.id.btn3:
                SoundPoolUtils.playSound(2);
                break;

            case R.id.btn4:
                SoundPoolUtils.playSound(3);
                break;

            case R.id.btn5:
                SoundPoolUtils.playSound(4);
                break;

            case R.id.btn6:
                SoundPoolUtils.playSound(5);
                break;

            case R.id.btn7:
                SoundPoolUtils.playSound(6);
                break;

            case R.id.btn8:
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        SoundPoolUtils.playSound(7);
                    }
                };
                timer.schedule(timerTask , 1000 , 1000);
                break;

            case R.id.btn9:
                SoundPoolUtils.playSound(8);
                break;

            default:
                break;
        }
    }
}
