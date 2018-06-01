package com.zhr.test.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.VideoView;

public class OneLevelCache{

    private static OneLevelCache instance;
    private final DiskCache mDiskCache;

    public static OneLevelCache getInstance(){
        if(instance == null){
            synchronized (OneLevelCache.class){
                if(instance == null){
                    instance = new OneLevelCache();
                }
            }
        }
        return instance;
    }

    private OneLevelCache(){
        mDiskCache = DiskCache.getInstance();
    }

    public void showVideoView(Context context , String url , final VideoView videoView) {
        final String proxyUrl = mDiskCache.getProxyUrl(context, url);
        videoView.setVideoPath(proxyUrl);
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVideoPath(proxyUrl);
                videoView.start();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                videoView.setVideoPath(proxyUrl);
                videoView.start();
                return true;
            }
        });
    }
}
