package com.zhr.test.util;

import android.os.Handler;

public class ThreadUtils {
	/**
	 * 运行在子线程
	 */
	public static void runInThread(Runnable r) {
		new Thread(r).start();
	}

	private static Handler hanlder = new Handler();

	/**
	 * 运行在主线程
	 */
	public static void runUnThread(Runnable r) {
		// new Message -->sendMessage-->handleMessaeg() r.run();
		hanlder.post(r);
	}
}
