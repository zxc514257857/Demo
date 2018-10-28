package com.zhr.test.util;

import android.text.TextUtils;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    private static final String TAG = MD5Utils.class.getSimpleName();

    /**
     * 把图片URL经过MD5加密生成唯一的key值，避免了URL中可能含有非法字符问题
     * @param key
     * @return
     */
    public static String hashKeyForDisk(String key) {
        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't get hashKey for disk because key is null");
            return null;
        }
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
