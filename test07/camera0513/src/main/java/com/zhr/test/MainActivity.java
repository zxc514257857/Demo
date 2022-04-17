package com.zhr.test;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Context mContext = MainActivity.this;
    private TextureView mTev;
    private Handler mHandler;
    private HandlerThread mThread;
    private CaptureRequest.Builder mPreviewBuilder;
    private Size mPreviewSize;
    private Range<Integer>[] mCameraFpsRanges;
    private Button mBt1;
    private ImageReader mImageReader;
    private CameraDevice mCameraDevice;
    private CameraCaptureSession mCaptureSession;
    private boolean mGetFrame = false;
    private Boolean mIsFlashAva;
    private boolean mIsCameraPerAva;

    /**
     * Camera2 用于android5.0 , 取代Camera1
     * styles中用了秒开全屏启动页方案
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTev = findViewById(R.id.tev);
        findViewById(R.id.bt1).setOnClickListener(this);
    }

    private void initPermission() {
        Log.e(TAG, "initPermission!");
        // 1,获取相机打开权限
        AndPermission.with(mContext).runtime().permission(Permission.Group.CAMERA, Permission.Group.STORAGE)
            .onGranted(new Action<List<String>>() {
                @Override
                public void onAction(List<String> permissions) {
                    Toast.makeText(mContext, "Request Permission Successful!", Toast.LENGTH_SHORT).show();
                    for (String per : permissions){
                        if("android.permission.CAMERA".equals(per)){
                            mIsCameraPerAva = true;
                        }
                    }
                }
            })
            .onDenied(new Action<List<String>>() {
                @Override
                public void onAction(@NonNull List<String> permissions) {
                    mIsCameraPerAva = false;
                }
            })
            .start();
    }

    private void initLooper() {
        Log.e(TAG, "initLooper!");
        // 2,创建相机子线程
        mThread = new HandlerThread("CameraThread");
        mThread.start();
        mHandler = new Handler(mThread.getLooper());
    }

    private void closeLooper() {
        mThread = null;
        mHandler = null;
    }

    private void closeCamera() {
        if (null != mCaptureSession) {
            mCaptureSession.close();
            mCaptureSession = null;
        }
        if (null != mCameraDevice) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
        if (null != mImageReader) {
            mImageReader.close();
            mImageReader = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        initPermission();
        initLooper();
        if (mTev.isAvailable()) {
            Log.e(TAG, "textureView is available!");
            openCamera();
        } else {
            Log.e(TAG, "textureView is not available!");
            mTev.setSurfaceTextureListener(this);
        }
        mTev.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTev.setVisibility(View.INVISIBLE);
        closeCamera();
        closeLooper();
    }

    /************************************************************************************************/
    /**
     * 当TextureView可用时调用
     * @param surfaceTexture
     * @param i
     * @param i1
     */
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        openCamera();
    }

    private void openCamera() {
        try {
            Log.e(TAG, "openCamera!");
            // 4,获取CameraManager---主要有两个功能：获取预览大小和openCamera
            CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            if (manager != null) {
                // cameraId = 0 表示后置摄像头；cameraId = 1 表示前置摄像头
                String[] cameraIdList = manager.getCameraIdList();
                Log.e(TAG, "cameraIdList:" + Arrays.toString(cameraIdList));
                final String cameraId = cameraIdList[0];
                // 5,获取CameraCharacteristics(前置或是后置摄像头所支持的功能特性)
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                // 获取相机的fps范围
                mCameraFpsRanges = characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                if (null != mCameraFpsRanges) {
                    Log.e(TAG, "cameraFpsRanges:" + mCameraFpsRanges[0]);
                }
                // 判断是否有闪光灯
                mIsFlashAva = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                Log.e(TAG, "isFlashAva:" + mIsFlashAva);
                // 获取目前设置的摄像头为前置(0)或是后置(1)
                Integer cameraFaceInt = characteristics.get(CameraCharacteristics.LENS_FACING);
                Log.e(TAG, "cameraFaceInt:" + cameraFaceInt);
                // 获取摄像头硬件支持等级 LEGACY(2) < LIMITED(0) < FULL(1) < LEVEL_3(3)
                Integer cameraSupLevel = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                Log.e(TAG, "cameraSupLevel:" + cameraSupLevel);
                // 获取摄像头详细的支持能力(0-12)  详见Camera2 APi：https://developer.android.google.cn/reference/android/hardware/camera2/package-summary
                int[] cameraAvaCap = characteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
                Log.e(TAG, "cameraAvaCap:" + Arrays.toString(cameraAvaCap));
                // 6,获取streamConfigurationMap
                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                // 7,获取previewSize
                if (map != null) {
                    // TextureView支持的预览尺寸
//                    Size[] outputSizes = map.getOutputSizes(SurfaceTexture.class);
                    // 摄像头支持的预览尺寸
                    Size[] outputSizes = map.getOutputSizes(ImageFormat.JPEG);
                    Log.e(TAG, "outputSizes: " + Arrays.toString(outputSizes));
                    // 获取支持的最大预览尺寸
//                    mMaxPreviewSize = Collections.max(Arrays.asList(outputSizes), new CompareSizesByArea());
                    if (null != outputSizes && outputSizes.length > 0) {
                        for (Size size : outputSizes) {
                            // 如果有1920 * 1080尺寸则使用
                            if (size.getWidth() == 1920) {
                                if (size.getHeight() == 1080) {
                                    mPreviewSize = size;
                                    break;
                                } else {
                                    mPreviewSize = size;
                                }
                            } else {
                                if (size.getHeight() == 1920) {
                                    mPreviewSize = size;
                                }
                            }
                        }
                    }
                    Log.e(TAG, "PreviewSize: " + mPreviewSize);
                }
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    initPermission();
                }
                if(mIsCameraPerAva){
                    manager.openCamera(cameraId, mCameraDeviceSateCallBack, mHandler);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TextureView缓冲区大小变化时调用
     * @param surfaceTexture
     * @param i
     * @param i1
     */
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {}

    /**
     * TextureView销毁时调用  一般返回为true
     * @param surfaceTexture
     * @return
     */
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return true;
    }

    /**
     * TextureView每更新一帧数据则回调一次
     * @param surfaceTexture
     */
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        Log.e(TAG, "onSurfaceTextureUpdated!");
    }

    /************************************************************************************************/

    private CameraDevice.StateCallback mCameraDeviceSateCallBack = new CameraDevice.StateCallback() {

        /**
         * 摄像头正常打开时调用
         * @param cameraDevice
         */
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            // 5,获取CameraDevice
            mCameraDevice = cameraDevice;
            startPreview();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            stopPreview();
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            stopPreview();
        }
    };

    private void startPreview(){
        try {
            Log.e(TAG, "startPreview!");
            // 9,获取SurfaceTexture和Surface
            SurfaceTexture texture = mTev.getSurfaceTexture();
            texture.setDefaultBufferSize(mPreviewSize.getWidth() , mPreviewSize.getHeight());
            @SuppressLint("Recycle")
            Surface surface = new Surface(texture);
            // 10,获取CaptureRequest
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            // 绑定SurfaceTexture和Surface,显示摄像头预览
            mPreviewBuilder.addTarget(surface);
            if(null != mCameraFpsRanges){
                // 设置摄像头的FPS范围
                mPreviewBuilder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE , mCameraFpsRanges[0]);
            }
            // 设置摄像头为自动调焦
            mPreviewBuilder.set(CaptureRequest.CONTROL_AF_MODE , CameraMetadata.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            // 设置打开闪光灯
            if(null != mIsFlashAva){
                if(mIsFlashAva){
                    Log.e(TAG, "flash on!");
                    mPreviewBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                }
            }
            // 11,创建ImageReader
            mImageReader = ImageReader.newInstance(mPreviewSize.getWidth(), mPreviewSize.getHeight(), ImageFormat.JPEG, 2);
            mImageReader.setOnImageAvailableListener(mImageAvaListener , mHandler);
            // 绑定ImageReader回调
            Surface imageSurface = mImageReader.getSurface();
            mPreviewBuilder.addTarget(imageSurface);
            // 12,创建session
            mCameraDevice.createCaptureSession(Arrays.asList(surface , imageSurface) , mCameraCaptureSessionSateCallBack , mHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopPreview() {
        Log.e(TAG, "stopPreview!");
        mCameraDevice.close();
    }

    private ImageReader.OnImageAvailableListener mImageAvaListener = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader imageReader) {
            try {
                Image image = imageReader.acquireNextImage();
                if(mGetFrame){
                    ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                    Log.i(TAG , "onImageAvailable: ");
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "zhr"
                            + System.currentTimeMillis() + ".jpg"));
                    fos.write(bytes);
                    fos.close();
                    mGetFrame = false;
                }
                image.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    CameraCaptureSession.StateCallback mCameraCaptureSessionSateCallBack = new CameraCaptureSession.StateCallback() {

        /**
         * 通过session开始捕获
         * @param cameraCaptureSession
         */
        @Override
        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
            mCaptureSession = cameraCaptureSession;
            // 更新预览数据
            updatePreview();
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {

        }
    };

    private void updatePreview(){
        try {
            Log.e(TAG, "updatePreview!");
            mCaptureSession.setRepeatingRequest(mPreviewBuilder.build() , null , mHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                // 点击获取一帧数据
                mGetFrame = true;
                break;

            default:
                break;
        }
    }

    /**
     * 尺寸大小的比较方式
     */
    static class CompareSizesByArea implements Comparator<Size> {
        @Override
        public int compare(Size lhs, Size rhs) {
            // Long.signum() 的意思是返回值的正负号
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() - (long) rhs.getWidth() * rhs.getHeight());
        }
    }
}