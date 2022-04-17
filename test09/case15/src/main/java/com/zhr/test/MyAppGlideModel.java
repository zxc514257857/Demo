package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Glide的自定义配置
 * https://blog.csdn.net/qijingwang/article/details/101269984
 */
@GlideModule
public class MyAppGlideModel extends AppGlideModule {

    private static final String TAG = "MyAppGlideModel";

    /**
     * Glide的全局配置
     * @param context
     * @param builder
     */
    @SuppressLint("CheckResult")
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        // 自定义硬盘缓存  默认的文件夹名字叫 image_manager_disk_cache
        // InternalCacheDiskCacheFactory 指的是应用的内部存储位置
        // ExternalCacheDiskCacheFactory 指的是应用的外部存储位置
        builder.setDiskCache(new DiskLruCacheFactory(context.getCacheDir().getPath() +  File.separator + "GlideDiskCache", 500 * 1024 *1024));
        // 自定义内存缓存
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                // 缓存多少屏图片（一般设置两屏）
                .setMemoryCacheScreens(2) .build();
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
        builder.setBitmapPool(new LruBitmapPool(30));
        // 设置GLide Log等级
        builder.setLogLevel(Log.VERBOSE);
        // Glide的默认图片质量是RGB565
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.format(DecodeFormat.PREFER_RGB_565);
        builder.setDefaultRequestOptions(requestOptions);
    }

    /**
     * Glide注册组件配置
     * @param context
     * @param glide
     * @param registry
     */
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        // 设置请求方式为Okhttp，并设置OkhttpClient的证书 及超时时间
        // Glide 默认图片加载使用的 HttpUrlConnection
//        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(UnsafeOkHttpClient.getUnsafeOkHttpClient());
//        registry.replace(GlideUrl.class, InputStream.class,factory);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
        registry.replace(GlideUrl.class , InputStream.class , new OkHttpUrlLoader.Factory(client));
    }

    // 自定义工具类修改OkHttpClient证书和超时时间
    private static class UnsafeOkHttpClient {
        public static OkHttpClient getUnsafeOkHttpClient() {
            try {
                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

                builder.connectTimeout(50, TimeUnit.SECONDS);
                builder.readTimeout(50, TimeUnit.SECONDS);
                builder.writeTimeout(50, TimeUnit.SECONDS);
                return builder.build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 是否解析AndroidManifest文件，Glide4.0之前 需要在AndroidManifest文件中配置GlideModel
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}