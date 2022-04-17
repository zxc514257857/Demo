package com.zhr.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * 摄像机拍图工具类
 */
public class CameraTakingUtils {

	private static final String TAG = CameraTakingUtils.class.getSimpleName();
	private static CameraTakingUtils mInstance;
	private static Camera mCamera;
	private ImageView mIv;
	private static SurfaceView mSv;
    private IConnectionManager mManager;
	private Socket mSocket;
	private int mVtType;
	private int mTimeDay;
	private int mTimeSec;
	private int mCount = 0;

	private CameraTakingUtils(ImageView iv , SurfaceView sv){
		this.mIv = iv;
		this.mSv = sv;
	}

	public synchronized static CameraTakingUtils getInstance(ImageView iv , SurfaceView sv){
		if(mInstance == null){
			mInstance = new CameraTakingUtils(iv , sv);
		}
		return mInstance;
	}

	public void start(){
		if(mCamera == null) {
			Log.e(TAG, "camera = null");
			mCamera = getCameraInstance();
		}
		Log.e(TAG, mCamera == null ? "mCamera is null" : "mCamera is not null");
//        mCamera.takePicture(null , null , mPicture);
	}

	/**
	 * 打开一个Camera
	 * @return
	 */
	public static Camera getCameraInstance() {
        try {
			mCamera = Camera.open();
			mCamera.setDisplayOrientation(90);
			Log.e(TAG, "打开Camera成功");
			Camera.Parameters mParameters = mCamera.getParameters();
//				mParameters.setPictureSize(256 , 256);
//				mParameters.setPictureFormat(PixelFormat.RGB_888);
			// 可以用得到当前所支持的照片大小
			List<Size> ms = mParameters.getSupportedPictureSizes();
			// 默认最大拍照取最大清晰度的照片
			mParameters.setPictureSize(ms.get(0).width, ms.get(0).height);
			mCamera.setParameters(mParameters);
			final SurfaceHolder holder = mSv.getHolder();
			holder.addCallback(new SurfaceHolder.Callback() {
				@Override
				public void surfaceCreated(SurfaceHolder surfaceHolder) {
					try {
						mCamera.setPreviewDisplay(holder);
						mCamera.startPreview();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
					if (holder.getSurface() == null){
						// preview surface does not exist
						return;
					}

					// stop preview before making changes
					try {
						mCamera.stopPreview();
					} catch (Exception e){
						// ignore: tried to stop a non-existent preview
					}

					// set preview size and make any resize, rotate or
					// reformatting changes here

					// start preview with new settings
					try {
						mCamera.setPreviewDisplay(holder);
						mCamera.startPreview();
					} catch (Exception e){
						Log.d(TAG, "Error starting camera preview: " + e.getMessage());
					}
				}

				@Override
				public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

				}
			});
		} catch (Exception e) {
            Log.e(TAG, "打开Camera失败:" + e.toString());
        }
        return mCamera;
    }

    private PictureCallback mPicture = new PictureCallback() {

		@Override
        public void onPictureTaken(byte[] data, Camera camera) {
			// 默认是ARGB_8888格式
			Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
			// 设置可调焦距

			// 设置可调ROI 感兴趣区域的位置
//            bmp.createBitmap(bmp , 0 , 0 , bmp.getWidth(), bmp.getHeight());

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

			//将图片转换为256 *256的RGB_888格式
			// 用于显示的bitmap
			final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp, dstWidth, dstHeight, true);
			// 调发给服务端的角度
//			Matrix matrix = new Matrix();
//			matrix.setRotate(270);
			// TODO: 2019/4/27 需要调整为沿Y轴对称
			// 用于发给服务端的bitmap
//			Bitmap sendBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);

			int iIndex = 20;
			for (int row = 0; row < dstHeight; row++) {
				for (int col = 0; col < dstWidth; col++) {
					int pixel = scaledBitmap.getPixel(row, col);
					sendBuf[iIndex++] = (byte) (pixel & 0xff);
					sendBuf[iIndex++] = (byte) ((pixel >> 8) & 0xff);
					sendBuf[iIndex++] = (byte) ((pixel >> 16) & 0xff);
				}
			}
//			sendBitmap.recycle();

			new Thread(new Runnable() {
				@Override
				public void run() {
//					connectServerWithTCPSocket(ByteBuffer.allocate(scaledBitmap.getByteCount()).array());
					connectServerWithTCPSocket(sendBuf);
//					receive();
				}
			}).start();

			new Thread(new Runnable() {
				@Override
				public void run() {
					receiveClientWithTCPSocket();
				}
			}).start();

			mIv.setImageBitmap(scaledBitmap);

			// 获取Jpeg图片，并保存在sd卡上
			String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test";
			File dirF = new File(path);
			if(!dirF.exists()){
				dirF.mkdirs();
			}
			File pictureFile = new File(path + File.separator + String.valueOf(mTimeDay) + String.valueOf(mTimeSec)+ ".jpg");
			try {
				FileOutputStream fos = new FileOutputStream(pictureFile);
				fos.write(ByteBuffer.allocate(scaledBitmap.getByteCount()).array());
				fos.close();
				Log.e(TAG, "保存图成功");
			} catch (Exception e) {
				Log.e(TAG, "保存图片失败");
				e.printStackTrace();
			}
//			scaledBitmap.recycle();
			releaseCarema();
        }
    };

    /**
     * TCP方式 scoket
     */
    private void connectServerWithTCPSocket(byte[] sendBuf) {

        try {
			mSocket = new Socket("192.168.3.39", 6000);
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

    public void releaseCarema(){
        if(mCamera != null){
            mCamera.release();
            mCamera = null;
        }
    }
}
