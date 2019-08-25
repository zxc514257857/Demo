package com.zhr.test;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.HashMap;

public class SoundPoolUtils {

    private static final String TAG = "SoundPoolUtils";
    private static HashMap<String , Integer> mHashMap = new HashMap<>();
    private static SoundPool mSoundPool;
    private static boolean mInitComplete = false;

    public static void initSound(Context context){

        int maxStreams = 3;
        // android 5.0以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            SoundPool.Builder soundPoolBuilder = new SoundPool.Builder();
            // 设置最大的音频流数量
            soundPoolBuilder.setMaxStreams(maxStreams);
            // 设置音频流的属性
            AudioAttributes.Builder audioBuilder = new AudioAttributes.Builder();
            // 音频音量调节方式(系统音量调节、铃声音量调节、音乐音量调节、通话音量调节、提示音音量调节)
            audioBuilder.setLegacyStreamType(AudioManager.STREAM_SYSTEM);
            soundPoolBuilder.setAudioAttributes(audioBuilder.build());
            mSoundPool = soundPoolBuilder.build();
            // android 5.0以下
        }else {
            // 设置最大音频流的数量，设置音频音量调节方式，设置音频质量，默认为0
            mSoundPool = new SoundPool(maxStreams , AudioManager.STREAM_SYSTEM , 0);
        }
        // 一般都是加载res-raw文件夹下的音频资源文件，音频优先级设置为1即可
        mHashMap.put("beep" , mSoundPool.load(context, R.raw.beep, 1));
        mHashMap.put("dabaicai" , mSoundPool.load(context , R.raw.tts_dabaicai , 1));
        mHashMap.put("tudou" , mSoundPool.load(context , R.raw.tts_tudou , 1));
        mHashMap.put("qiezi" , mSoundPool.load(context , R.raw.tts_qiezi , 1));
        mHashMap.put("huanggua" , mSoundPool.load(context , R.raw.tts_huanggua , 1));
        mHashMap.put("yumi" , mSoundPool.load(context , R.raw.tts_yumi , 1));
        mHashMap.put("xihongshi" , mSoundPool.load(context , R.raw.tts_xihongshi , 1));
        mHashMap.put("shuaihui" , mSoundPool.load(context , R.raw.tts_shuaihui, 1));
        mHashMap.put("success" , mSoundPool.load(context , R.raw.tts_identify_success, 1));

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                mInitComplete = true ;
            }
        });
    }

    public static void playSound(int flag){
        if(mInitComplete){
            if(flag == 99){
                mSoundPool.play(mHashMap.get("beep") , 1 , 1 , 0 , 0 , 1);
            }else if(flag == 1){
                mSoundPool.play(mHashMap.get("dabaicai") , 1 , 1 , 0 , 0 , 1);
            }else if(flag == 2){
                mSoundPool.play(mHashMap.get("tudou") , 1 , 1 , 0 , 0 , 1);
            }else if(flag == 3){
                mSoundPool.play(mHashMap.get("qiezi") , 1 , 1 , 0 , 0 , 1);
            }else if(flag == 4){
                mSoundPool.play(mHashMap.get("huanggua") , 1 , 1 , 0 , 0 , 1);
            }else if(flag == 5){
                mSoundPool.play(mHashMap.get("yumi") , 1 , 1 , 0 , 0 , 1);
            }else if(flag == 6){
                mSoundPool.play(mHashMap.get("xihongshi") , 1 , 1 , 0 , 0 , 1);
            }else if(flag == 7){
                mSoundPool.play(mHashMap.get("shuaihui") , 1 , 1 , 0 , 0 , 1);
            }else {
                mSoundPool.play(mHashMap.get("success") , 1 , 1 , 0 , 0 , 1);
            }
        }
    }
}
