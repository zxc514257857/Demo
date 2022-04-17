package com.zhr.test;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * permission
 * cameraHandler和cameraHandlerThread
 * TextureView和SurfaceTexture    https://blog.csdn.net/qqicq2001/article/details/53334723
 * Cameramanager  CameraId  CameraCharacteristics  https://developer.android.google.cn/reference/android/hardware/camera2/CameraMetadata.html#REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE
 * CameraDevice
 * CameraCaptureSession
 * CaptureRequest
 * CaptureResult
 */
public class CameraSettingActivity extends AppCompatActivity {

    private AutoFitTextureView mAftv;
    private RelativeLayout mRl;
    private Button mBtn;
    private SeekBar mSb;
    private HandlerThread mCamereaBackgroundThread;
    private Handler mCamereaBackgroundHandler;

    private static final String TAG = "CameraSettingActivity";
    private Context mContext = CameraSettingActivity.this;
    private ImageReader mImageReader;

    private static final int STATE_PREVIEW = 0;
    private static final int STATE_WAITING_LOCK = 1;
    private static final int STATE_WAITING_PRECAPTURE = 2;
    private static final int STATE_WAITING_NON_PRECAPTURE = 3;
    private static final int STATE_PICTURE_TAKEN = 4;

    private int mState = STATE_PREVIEW;
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    private Integer mSensorOrientation;
    private Size mPreviewSize;
    private boolean mFlashSupported;
    private String mCameraId;
    private CameraDevice mCameraDevice;
    private CaptureRequest.Builder mPreviewRequestBuilder;
    private CameraCaptureSession mCaptureSession;
    private CaptureRequest mPreviewRequest;
    private File mFile;
    private ImageView mIv1;

    private int mVtType;
    private int mTimeDay;
    private int mTimeSec;
    private Socket mSocket;
    private CameraCharacteristics mCameraCharacteristics;
    private int mCount = 0;;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_setting);
        initView();
        initData();
    }

    private void initView() {
        mAftv = findViewById(R.id.aftv);
        mRl = findViewById(R.id.rl);
        mBtn = findViewById(R.id.btn);
        mSb = findViewById(R.id.sb);
        mIv1 = findViewById(R.id.iv1);
    }

    /**
     * 这个页面只是用于显示预览、设置拍照采集区域和设置照片拍摄清晰度
     */
    private void initData() {

        mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "zhr.jpg");

        // 在启动页申请权限
        AndPermission.with(mContext)
                .runtime()
                // 申请Camera权限以及存储权限
                .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                .start();

//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        takePicture();
                    }
                } , 1000 , 1000);
//            }
//        });

        mSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            /**
             * 进度条拖动改变时调用
             * @param seekBar
             * @param progress
             * @param fromTouch
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                Log.e(TAG, "onProgressChanged: " + progress + "," + fromTouch);
                try {
                    float minimumLens = mCameraCharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
                    float num = (((float) progress) * minimumLens / 100);
                    mPreviewRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * 进度条开始拖动时调用
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e(TAG, "onProgressChanged: " + "进度条开始拖动!");
            }

            /**
             * 进度条停止拖动时调用
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e(TAG, "onProgressChanged: " + "进度条停止拖动!");
            }
        });
    }

    private void takePicture() {
        lockFocus();
    }

    private void lockFocus() {
        try {
            // 摄像头对焦
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER , CameraMetadata.CONTROL_AF_TRIGGER_START);
            // Tell #mCaptureCallback to wait for the lock.
            mState = STATE_WAITING_LOCK;
            mCaptureSession.capture(mPreviewRequestBuilder.build(), mCaptureCallback, mCamereaBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 打开camera线程和Handler
        startCameraBackgroundThread();
        // 打开Camera
        startCamera();
    }

    @Override
    protected void onPause() {
        // 关闭Camera
        stopCamera();
        // 关闭camera线程
        stopCameraBackgroundThread();
        super.onPause();
    }

    /**
     * 打开Camera
     */
    private void startCamera() {
        // TextureView必须在manifest文件中对应的Application中或者Activity中开启硬件加速
        // 如果与TextureView关联的SurfaceTexture可以用于渲染，则返回true。返回true时，getSurfaceTexture返回有效的表面纹理
        if (mAftv.isAvailable()) {
            // 1504*1128
            Log.e(TAG, "startCamera: 111," + mAftv.getWidth() + "*" + mAftv.getHeight());
            // 若TextureView关联的SurfaceTexture可以使用时，打开Camera
            openCamera(mAftv.getWidth(), mAftv.getHeight());
        } else {
            Log.e(TAG, "startCamera: 222");
            // TextureView是和SurfaceTexture一起使用的 相比于SurfaceView优点是可以对View进行平移旋转变化等各种处理
            mAftv.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {

                /**
                 * SurfaceTexture可以使用时调用
                 * @param surfaceTexture
                 * @param width
                 * @param height
                 */
                @Override
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
                    // 若SurfaceTexture可以使用时，打开Camera  1920*1128
                    Log.e(TAG, "startCamera: 333," + width + "*" + height);
                    openCamera(width , height);
                }

                /**
                 * 当SurfaceTexture缓冲区大小改变时调用
                 * @param surfaceTexture
                 * @param width
                 * @param height
                 */
                @Override
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
                    // 若SurfaceTexture缓冲区大小改变时 设置SurfaceTexture的宽高  1504*1128
                    Log.e(TAG, "startCamera: 444," + width + "*" + height);
                    configureTransform(width, height);
                }

                /**
                 * 当SurfaceTexture销毁时调用
                 * 一般返回为true
                 * 返回true时，调用此方法表面纹理不会发生渲染；返回false时，需要手动调用release()
                 * @param surfaceTexture
                 * @return
                 */
                @Override
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    Log.e(TAG, "startCamera: 555");
                    return true;
                }

                /**
                 * 当SurfaceTexture更新时调用
                 * @param surfaceTexture
                 */
                @Override
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {}
            });
        }
    }

    /**
     * 若SurfaceTexture可以使用，则打开Camera
     * @param width
     * @param height
     */
    private void openCamera(int width, int height) {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                AndPermission.with(mContext)
                    .runtime()
                    // 申请Camera权限以及存储权限
                    .permission(Permission.CAMERA)
                    .start();
                return;
            }

            // 设置Camera输出参数
            setUpCameraOutputs(width, height);
            // 设置SurfaceTexture的宽高
            configureTransform(width, height);
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            if (cameraManager != null) {
                if (!mCameraOpenCloseLock.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
                    throw new RuntimeException("Time out waiting to lock camera opening.");
                }

                cameraManager.openCamera(mCameraId, new CameraDevice.StateCallback() {
                    /**
                     * 打开相机成功之后回调此方法
                     * 一般在这个方法中获取一个全局的CameraDevice实例，开启相机预览等操作
                     * @param cameraDevice
                     */
                    @Override
                    public void onOpened(@NonNull CameraDevice cameraDevice) {
                        mCameraOpenCloseLock.release();
                        mCameraDevice = cameraDevice;
                        // 要想预览、拍照等操作都是需要通过会话来实现，创建会话用于预览
                        createCameraPreviewSession();
                    }

                    /**
                     *
                     * @param cameraDevice
                     */
                    @Override
                    public void onDisconnected(@NonNull CameraDevice cameraDevice) {
                        mCameraOpenCloseLock.release();
                        cameraDevice.close();
                        mCameraDevice = null;
                    }

                    @Override
                    public void onError(@NonNull CameraDevice cameraDevice, int i) {
                        mCameraOpenCloseLock.release();
                        cameraDevice.close();
                        mCameraDevice = null;
                    }
                }, mCamereaBackgroundHandler);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCameraPreviewSession() {
        try {
            SurfaceTexture texture = mAftv.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(mPreviewSize.getWidth() , mPreviewSize.getHeight());
            Surface surface = new Surface(texture);
            // 通过CameraDevice创建捕获请求，在需要预览、拍照、再次预览的时候需要通过创建请求来完成
            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            // 设置surface作为预览数据的显示界面
            mPreviewRequestBuilder.addTarget(surface);
            // 创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，
            // 当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null表示在当前线程执行
            mCameraDevice.createCaptureSession(Arrays.asList(surface, mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                /**
                 * 会话开始处理捕获请求
                 * @param cameraCaptureSession
                 */
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    try {
                        // 摄像机已经关闭
                        if(null == mCameraDevice){
                            return;
                        }

                        // 当会话已经创建成功，开始展示预览
                        mCaptureSession = cameraCaptureSession;
                        // 设置 为持续对焦模式
//                        mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE , CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

                        mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE , CameraMetadata.CONTROL_AE_MODE_OFF);
                        mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE , CameraMetadata.CONTROL_AF_MODE_OFF);
                        mPreviewRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, 0.5f);

                        // 如有闪光灯，设置自动打开
                        setAutoFlash(mPreviewRequestBuilder);
                        // 开始展示摄像头预览
                        mPreviewRequest = mPreviewRequestBuilder.build();
                        // 设置反复捕获数据请求，这样预览界面一直有数据显示
                        mCaptureSession.setRepeatingRequest(mPreviewRequest, mCaptureCallback , mCamereaBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * 会话处理捕获请求失败
                 * @param cameraCaptureSession
                 */
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {}

            } , null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAutoFlash(CaptureRequest.Builder previewRequestBuilder) {
        if(mFlashSupported){
            previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE , CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
        }
    }

    /**
     * 关闭Camera
     */
    private void stopCamera() {
        try {
            mCameraOpenCloseLock.acquire();
            if(null != mCaptureSession){
                mCaptureSession.close();
                mCaptureSession = null;
            }
            if(null != mCameraDevice){
                mCameraDevice.close();
                mCameraDevice = null;
            }
            if(null != mImageReader){
                mImageReader.close();
                mImageReader = null;
            }
        } catch (Exception e) {

        } finally {
            mCameraOpenCloseLock.release();
        }
    }

    CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {

        private void process(CaptureResult result) {
            switch (mState) {
                case STATE_PREVIEW: {
                    // We have nothing to do when the camera preview is working normally.
                    Log.e(TAG, "process: STATE_PREVIEW");
                    break;
                }

                case STATE_WAITING_LOCK: {
                    Log.e(TAG, "process: STATE_WAITING_LOCK");
                    // 如果设置为对焦好了之后拍照 把焦距取消 逻辑不会走到这里

//                    Integer afState = result.get(CaptureResult.CONTROL_AF_STATE);
//                    if (afState == null) {
//                        Log.e(TAG, "process: 111");
//                        captureStillPicture();
//                    } else if (CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED == afState || CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED == afState) {
//                        // CONTROL_AE_STATE can be null on some devices
//                        Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
//                        if (aeState == null || aeState == CaptureResult.CONTROL_AE_STATE_CONVERGED) {
//                            mState = STATE_PICTURE_TAKEN;
//                            Log.e(TAG, "process: 222");
                            captureStillPicture();
//                        } else {
//                            Log.e(TAG, "process: 333");
//                            runPrecaptureSequence();
//                        }
//                    }
                    break;
                }

                case STATE_WAITING_PRECAPTURE: {
                    Log.e(TAG, "process: STATE_WAITING_PRECAPTURE");
                    // CONTROL_AE_STATE can be null on some devices
                    Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
                    if (aeState == null || aeState == CaptureResult.CONTROL_AE_STATE_PRECAPTURE || aeState == CaptureRequest.CONTROL_AE_STATE_FLASH_REQUIRED) {
                        mState = STATE_WAITING_NON_PRECAPTURE;
                    }
                    break;
                }

                case STATE_WAITING_NON_PRECAPTURE: {
                    Log.e(TAG, "process: STATE_WAITING_NON_PRECAPTURE");
                    // CONTROL_AE_STATE can be null on some devices
                    Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
                    if (aeState == null || aeState != CaptureResult.CONTROL_AE_STATE_PRECAPTURE) {
                        mState = STATE_PICTURE_TAKEN;
                        captureStillPicture();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        @Override
        public void onCaptureProgressed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureResult partialResult) {
            process(partialResult);
        }

        @Override
        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
            process(result);
        }
    };

    private void captureStillPicture() {
        try {
            if (null == mCameraDevice) {
                return;
            }
            // This is the CaptureRequest.Builder that we use to take a picture.
            final CaptureRequest.Builder captureBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(mImageReader.getSurface());

            // Use the same AE and AF modes as the preview.
//            captureBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE , CameraMetadata.CONTROL_AE_MODE_OFF);
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE , CameraMetadata.CONTROL_AF_MODE_OFF);
            mPreviewRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, 0.5f);

            setAutoFlash(captureBuilder);

            // Orientation
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION , (ORIENTATIONS.get(rotation) + mSensorOrientation + 270) % 360);

            CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {

                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    Log.e(TAG, mFile.toString());
                    unlockFocus();
                }
            };

            mCaptureSession.stopRepeating();
            mCaptureSession.abortCaptures();
            mCaptureSession.capture(captureBuilder.build(), captureCallback , null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void runPrecaptureSequence() {
        try {
            // This is how to tell the camera to trigger.
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER , CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER_START);
            // Tell #mCaptureCallback to wait for the precapture sequence to be set.
            mState = STATE_WAITING_PRECAPTURE;
            mCaptureSession.capture(mPreviewRequestBuilder.build(), mCaptureCallback, mCamereaBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void unlockFocus() {
        try {
            // Reset the auto-focus trigger
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
            setAutoFlash(mPreviewRequestBuilder);
            mCaptureSession.capture(mPreviewRequestBuilder.build(), mCaptureCallback, mCamereaBackgroundHandler);
            // After this, the camera will go back to the normal state of preview.
            mState = STATE_PREVIEW;
            mCaptureSession.setRepeatingRequest(mPreviewRequest, mCaptureCallback, mCamereaBackgroundHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Camera输出参数
     * @param width
     * @param height
     */
    private void setUpCameraOutputs(int width, int height) {
        try {
            CameraManager cameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
            if(cameraManager != null){
                // 获取当前连接的所有Camera的cameraID集合
                String[] cameraIdList = cameraManager.getCameraIdList();
                // 遍历所有的cameraId
                // 一般来说 android摄像头 后置的为0, 前置的为1
                Log.e(TAG, "cameraId: " + cameraIdList[0]);
                // 查询此cameraId对应的Camera所支持的功能(摄像头特性)
                mCameraCharacteristics = cameraManager.getCameraCharacteristics(cameraIdList[0]);
                // 获取摄像头的朝向  0表示前置摄像头(CameraCharacteristics.LENS_FACING_FRONT)
                // 1表示后置摄像头(CameraCharacteristics.LENS_FACING_BACK)   2表示除前置后置之外的其他摄像头(CameraCharacteristics.LENS_FACING_EXTERNAL)
                Integer integer = mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                // 如果摄像头为后置摄像头
                if(integer != null && integer == CameraCharacteristics.LENS_FACING_BACK){
                    // 整体上摄像头支持的功能
                    // 摄像头可调功能等级 4表示外接的摄像头CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_EXTERNAL
                    // 0表示摄像头有最基本的功能, 还支持一些额外的高级功能, 这些高级功能是LEVEL_FULL的子集  CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED
                    // 1表示摄像头支持对每一帧数据进行控制,还支持高速率的图片拍摄 CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL
                    // 2表示摄像头基本没有额外功能 CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY
                    // 3表示摄像头支持YUV后处理和Raw格式图片拍摄, 还支持额外的输出流配置 CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_3
                    // 各个等级从支持的功能多少排序为: LEGACY(2) < LIMITED(0) < FULL(1) < LEVEL_3(3)
                    Integer hardwareLevel  = mCameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                    // 2(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY)
                    Log.e(TAG, "hardwareLevel: " + hardwareLevel);
                    // 具体的摄像头所支持的能力
                    // 0表示摄像头支持的最小功能集  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE
                    // 1表示可以手动控制摄像头（例如自动曝光等3A算法，可以绕过自动对焦） CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR
                    // 2表示摄像头支持图像后处理阶段的基本手动控制  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING
                    // 3表示摄像头支持输出RAW缓冲区和元数据以解释它们  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_RAW
                    // 4表示摄像头支持零快门延迟重新处理用例  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_PRIVATE_REPROCESSING
                    // 5表示在内置3A算法运行时，摄像头设备支持准确报告许多传感器控件的传感器设置 CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_READ_SENSOR_SETTINGS
                    // 6表示当后处理设置设置为快速时，摄像头支持以 >= 20帧/秒（至少未压缩的YUV格式）捕获高分辨率图像  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_BURST_CAPTURE
                    // 7表示摄像头支持YUV_420_888重新处理用例  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_YUV_REPROCESSING
                    // 8表示摄像头可以从其视野产生深度测量  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT
                    // 9表示摄像头支持约束高速视频录制（帧速率> = 120fps）用例  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_CONSTRAINED_HIGH_SPEED_VIDEO
                    // 10表示摄像头支持MOTION_TRACKING值 CaptureRequest#CONTROL_CAPTURE_INTENT，将最大曝光时间限制为20 ms。这限制了捕获图像的运动模糊，从而为诸如图像稳定或增强现实的用例产生更好的图像跟踪结果
                    // CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_MOTION_TRACKING
                    // 11表示摄像头是由两个或更多物理相机支持的逻辑相机  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_LOGICAL_MULTI_CAMERA
                    // 12表示摄像头是不包含滤色器阵列的单色相机，对于YUV_420_888流，U和V平面上的像素值都是128  CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_MONOCHROME
                    int[] hardwareCapabilities = mCameraCharacteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
                    if(hardwareCapabilities != null && hardwareCapabilities.length > 0){
                        for(int hardwareCapability : hardwareCapabilities){
                            // 0(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE)
                            Log.e(TAG, "hardwareCapability: " + hardwareCapability);
                        }
                    }
                    // 摄像头支持的可用流的配置，包括最小帧间隔、不同格式、大小组合的失帧时长
                    StreamConfigurationMap streamConfigurationMap = mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                    // 设置摄像头输出值的大小
                    Size[] outputSizes = new Size[0];
                    if (streamConfigurationMap != null) {
                        outputSizes = streamConfigurationMap.getOutputSizes(ImageFormat.JPEG);
                    }
                    // 把数组转换为集合
                    List<Size> sizes = Arrays.asList(outputSizes);
                    // 获取摄像头支持的最大尺寸
                    Size largest = Collections.max(sizes, new CompareSizesByArea());
                    // 初始化ImageReader 获取帧数据的尺寸和格式以及每次最多可以获取几帧数据
                    mImageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(), ImageFormat.JPEG , 3);

                    // 监听ImageReader事件，当有图像数据可用时回调onImageAvailable方法，它的参数就是预览帧数据
                    mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                        @Override
                        public void onImageAvailable(final ImageReader imageReader) {

                            // 获取图像队列里面的图片 最后帧图片
                            Image image = imageReader.acquireNextImage();
                            int maxImages = imageReader.getMaxImages();
                            Log.e(TAG, "maxImages: " + maxImages);
                            // 获取改图片的像素矩阵
                            ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            Log.e(TAG, "bytes.length: " + bytes.length);
                            final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            if(bmp != null){
                                Bitmap bitmap = Bitmap.createBitmap(bmp, (bmp.getWidth() - bmp.getHeight()) / 2 , 0 , bmp.getHeight(), bmp.getHeight());
                                Calendar calendar = Calendar.getInstance();
                                mTimeDay = Integer.parseInt(String.valueOf(calendar.get(Calendar.YEAR))
                                        + String.valueOf(calendar.get(Calendar.MONTH) + 1) + String.valueOf(calendar.get(Calendar.DATE)));
                                mTimeSec = Integer.parseInt(String.valueOf(calendar.get(Calendar.HOUR))
                                        + String.valueOf(calendar.get(Calendar.MINUTE)) + String.valueOf(calendar.get(Calendar.SECOND))
                                        + String.valueOf(calendar.get(Calendar.MILLISECOND)));
                                Log.e(TAG, "timeDay: " + mTimeDay);
                                Log.e(TAG, "timeSec: " + mTimeSec);
                                // 图片宽 + 高 + 年月日 + 时分秒 + 菜品类型 + RGB图片
                                final byte[] sendBuf = new byte[20 + 256 * 256 * 3];
                                int dstWidth = 256;
                                int dstHeight = 256;
                                // int 类型转byte[]
                                for (int i = 0; i < 4; i++) {
                                    sendBuf[i] = (byte) (dstWidth >> (24 - i * 8));
                                    Log.i(TAG, "sendBufWidth: " + sendBuf[i]);
                                }
                                for (int i = 4; i < 8; i++) {
                                    sendBuf[i] = (byte) (dstHeight >> (24 - (i - 4) * 8));
                                    Log.i(TAG, "sendBufHeight: " + sendBuf[i]);
                                }
                                for (int i = 8; i < 12; i++) {
                                    sendBuf[i] = (byte) (mTimeDay >> (24 - (i - 8) * 8));
                                    Log.i(TAG, "sendBufTimeDay: " + sendBuf[i]);
                                }
                                for (int i = 12; i < 16; i++) {
                                    sendBuf[i] = (byte) (mTimeSec >> (24 - (i - 12) * 8));
                                    Log.i(TAG, "sendBufTimeSec: " + sendBuf[i]);
                                }
                                // 菜品类型id
                                mVtType = new Random().nextInt(60) + 1;
                                for (int i = 16; i < 20; i++) {
                                    sendBuf[i] = (byte) (mVtType >> (24 - (i - 16) * 8));
                                    Log.i(TAG, "sendVtType: " + sendBuf[i]);
                                }
                                final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, true);
                                int iIndex = 20;
                                for (int row = 0; row < dstHeight; row++) {
                                    for (int col = 0; col < dstWidth; col++) {
                                        int pixel = scaledBitmap.getPixel(row, col);
                                        sendBuf[iIndex++] = (byte) (pixel & 0xff);
                                        sendBuf[iIndex++] = (byte) ((pixel >> 8) & 0xff);
                                        sendBuf[iIndex++] = (byte) ((pixel >> 16) & 0xff);
                                    }
                                }
                                ThreadUtils.runInThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        connectServerWithTCPSocket(sendBuf);
                                    }
                                });
                                ThreadUtils.runInThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        receiveClientWithTCPSocket();
                                    }
                                });

                                Log.e(TAG, "onImageAvailable: 111");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mIv1.setImageBitmap(scaledBitmap);
                                    }
                                });


                            // 图片保存本地
//                                byte[] array = ByteBuffer.allocate(scaledBitmap.getByteCount()).array();
//                                try {
//                                    FileOutputStream fos = new FileOutputStream(mFile);
//                                    fos.write(array);
//                                    fos.close();
//                                    Log.e(TAG, "保存图成功");
//                                } catch (Exception e) {
//                                    Log.e(TAG, "保存图片失败");
//                                    e.printStackTrace();
//                                }

                            }else {
                                Log.e(TAG, "onImageAvailable: 222");
                            }
                            image.close();
                        }
                    } , mCamereaBackgroundHandler);

                    // 判断是否需要根据传感器交换尺寸获取预览图的大小
                    // 0(Surface.ROTATION_0)
                    // 1(Surface.ROTATION_90)
                    // 2(Surface.ROTATION_180)
                    // 3(Surface.ROTATION_270)
                    int displayRotation = getWindowManager().getDefaultDisplay().getRotation();
                    // 3(Surface.ROTATION_270)
                    Log.e(TAG, "displayRotation: " + displayRotation);
                    // 获取当前屏幕相对于"自然方向的旋转角度"
                    // 0(Surface.ROTATION_0)
                    mSensorOrientation = mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                    Log.e(TAG, "mSensorOrientation: " + mSensorOrientation);
                    // 是否需要交换尺寸
                    boolean swappedDimensions = false;
                    switch (displayRotation) {
                        case Surface.ROTATION_0:
                        case Surface.ROTATION_180:
                            if(mSensorOrientation == 90 || mSensorOrientation == 270){
                                swappedDimensions = true;
                            }
                            break;
                        case Surface.ROTATION_90:
                        case Surface.ROTATION_270:
                            if(mSensorOrientation == 0 ||mSensorOrientation == 180){
                                swappedDimensions = true;
                            }
                            break;
                        default:
                            Log.e(TAG, "Display rotation is invalid: " + displayRotation);
                            break;
                    }
                    Point displaySize = new Point();
                    getWindowManager().getDefaultDisplay().getSize(displaySize);
                    int rotatedPreviewWidth = width;
                    int rotatedPreviewHeight = height;
                    int maxPreviewWidth = displaySize.x;
                    int maxPreviewHeight = displaySize.y;
                    if(swappedDimensions){
                        rotatedPreviewWidth = height;
                        rotatedPreviewHeight = width;
                        maxPreviewWidth = displaySize.y;
                        maxPreviewHeight = displaySize.x;
                    }
                    if(maxPreviewWidth > 1920){
                        maxPreviewWidth = 1920;
                    }
                    if(maxPreviewHeight > 1080){
                        maxPreviewHeight = 1080;
                    }

                    // 获取最佳的预览方向和尺寸
                    if (streamConfigurationMap != null) {
                        mPreviewSize = chooseOptimalSize(streamConfigurationMap.getOutputSizes(SurfaceTexture.class), rotatedPreviewWidth, rotatedPreviewHeight, maxPreviewWidth, maxPreviewHeight, largest);
                    }
                    // 判断当前的横竖屏关系
                    // 1 竖屏(Configuration.ORIENTATION_PORTRAIT)
                    // 2 横屏(Configuration.ORIENTATION_LANDSCAPE)
                    int orientation = getResources().getConfiguration().orientation;
                    // 2
                    Log.e(TAG, "orientation: " + orientation);
                    // 将TextureView的纵横关系和预览的纵横关系相匹配
                    if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                        mAftv.setAspectRatio(mPreviewSize.getWidth(), mPreviewSize.getHeight());
                    }else {
                        mAftv.setAspectRatio(mPreviewSize.getHeight(), mPreviewSize.getWidth());
                    }

                    // 判断是否支持闪光灯
                    Boolean available = mCameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    mFlashSupported = available == null ? false : available;
                    mCameraId = cameraIdList[0];
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置SurfaceTexture的宽高
     * @param width
     * @param height
     */
    private void configureTransform(int width, int height) {
        if(null == mAftv || null == mPreviewSize){
            return;
        }
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        RectF viewRect = new RectF(0 , 0 , width , height);
        RectF bufferRect = new RectF(0 , 0 , mPreviewSize.getHeight() , mPreviewSize.getWidth());
        float centerX = viewRect.centerX();
        float centerY = viewRect.centerY();
        if(Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation){
            bufferRect.offset(centerX - bufferRect.centerX() , centerY - bufferRect.centerY());
            matrix.setRectToRect(viewRect, bufferRect, Matrix.ScaleToFit.FILL);
            float scale = Math.max((float) height / mPreviewSize.getHeight(), (float) width / mPreviewSize.getWidth());
            matrix.postScale(scale, scale, centerX, centerY);
            matrix.postRotate(90 * (rotation - 2), centerX, centerY);
        }else if(Surface.ROTATION_180 == rotation){
            matrix.postRotate(180 , centerX ,centerY);
        }
        mAftv.setTransform(matrix);
    }

    /**
     * 获取最佳的预览尺寸
     * @param choices
     * @param textureViewWidth
     * @param textureViewHeight
     * @param maxWidth
     * @param maxHeight
     * @param aspectRatio
     * @return
     */
    private static Size chooseOptimalSize(Size[] choices, int textureViewWidth, int textureViewHeight, int maxWidth, int maxHeight, Size aspectRatio) {
        // Collect the supported resolutions that are at least as big as the preview Surface
        List<Size> bigEnough = new ArrayList<>();
        // Collect the supported resolutions that are smaller than the preview Surface
        List<Size> notBigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            if (option.getWidth() <= maxWidth && option.getHeight() <= maxHeight && option.getHeight() == option.getWidth() * h / w) {
                if (option.getWidth() >= textureViewWidth && option.getHeight() >= textureViewHeight) {
                    bigEnough.add(option);
                } else {
                    notBigEnough.add(option);
                }
            }
        }

        // Pick the smallest of those big enough. If there is no one big enough, pick the
        // largest of those not big enough.
        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else if (notBigEnough.size() > 0) {
            return Collections.max(notBigEnough, new CompareSizesByArea());
        } else {
            Log.e(TAG, "Couldn't find any suitable preview size");
            return choices[0];
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

    /**
     * 打开camera线程和Handler
     */
    private void startCameraBackgroundThread() {
        mCamereaBackgroundThread = new HandlerThread("CameraBackground");
        mCamereaBackgroundThread.start();
        mCamereaBackgroundHandler = new Handler(mCamereaBackgroundThread.getLooper());
    }

    /**
     * 关闭Camera线程和Handler
     */
    private void stopCameraBackgroundThread() {
        try {
            mCamereaBackgroundThread.quitSafely();
            mCamereaBackgroundThread.join();
            mCamereaBackgroundThread = null;
            mCamereaBackgroundHandler = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TCP方式 scoket
     */
    private void connectServerWithTCPSocket(byte[] sendBuf) {

        try {
            mSocket = new Socket("192.168.3.14", 6000);
            if (mSocket.isConnected()) {
                Log.e(TAG, "connect to Server success");
            }else {
                Log.e(TAG, "connect to Server fail1");
            }
            // 设置流的超时时间
//            socket.setSoTimeout(5000);
            // 发送信息
            OutputStream outputStream = mSocket.getOutputStream();
            outputStream.write(sendBuf , 0 , sendBuf.length);
//			socket.shutdownOutput();
            outputStream.flush();
            outputStream.close();
        } catch (UnknownHostException e) {
            Log.e(TAG, "connect to Server fail2");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "connect to Server fail3");
            e.printStackTrace();
        }
    }

    private void receiveClientWithTCPSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(7000);
            Log.e(TAG, "打开服务器成功");
            Socket clientSocket;
            while (true){
                clientSocket = serverSocket.accept();
                Log.e(TAG, "客户端:" + clientSocket.getInetAddress().getHostAddress() + "已连接到服务器");
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "utf-8"));
                String data = bufferedReader.readLine();
                String[] split = data.split(",");
                String time = split[0];
                String id = split[1];
                Log.e(TAG, "time:" + time + ",id:" + id);
                Log.e(TAG, "timeSec:" + String.valueOf(mVtType) + ",type:" + String.valueOf(mTimeDay) + String.valueOf(mTimeSec));
                Log.e(TAG, "收到消息:" + data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
