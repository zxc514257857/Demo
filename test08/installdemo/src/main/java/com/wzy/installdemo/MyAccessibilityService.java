package com.wzy.installdemo;

import android.accessibilityservice.AccessibilityService;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = "[TAG]";
    private Map<Integer, Boolean> handleMap = new HashMap<>();

    public static int INVOKE_TYPE = 0;
    public static final int TYPE_KILL_APP = 1;
    public static final int TYPE_INSTALL_APP = 2;
    public static final int TYPE_UNINSTALL_APP = 3;

    public static void reset() {
        INVOKE_TYPE = 0;
    }

    private AccessibilityEvent mEvent;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // TODO Auto-generated method stub
        this.processAccessibilityEnvent(event);
    }

    private void processAccessibilityEnvent(AccessibilityEvent event) {

        Log.e("test11", AccessibilityEvent.eventTypeToString(event.getEventType()));
        if (event.getSource() == null) {
            Log.e("test22", "the source = null");
        } else {
            Log.e("test333", "event = " + event.toString());
            switch (INVOKE_TYPE) {
                case TYPE_KILL_APP:
                    processKillApplication(event);
                    break;
                case TYPE_INSTALL_APP:
                    mEvent = event;
                    Log.e("test444", "event = " + event.toString());
                    processinstallApplication(event);
                    break;
                case TYPE_UNINSTALL_APP:
                    processUninstallApplication(event);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        return true;

    }

    @Override
    public void onInterrupt() {
        // TODO Auto-generated method stub

    }

    private void processUninstallApplication(AccessibilityEvent event) {

        if (event.getSource() != null) {
            if (event.getPackageName().equals("com.android.packageinstaller")) {
                List<AccessibilityNodeInfo> ok_nodes = event.getSource().findAccessibilityNodeInfosByText("确定");
                if (ok_nodes != null && !ok_nodes.isEmpty()) {
                    AccessibilityNodeInfo node;
                    for (int i = 0; i < ok_nodes.size(); i++) {
                        node = ok_nodes.get(i);
                        if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }

                }
            }
        }

    }

    private void processinstallApplication(AccessibilityEvent event) {

        if (event.getSource() != null) {
            if ("com.android.packageinstaller".equals(event.getPackageName())) {
                List<AccessibilityNodeInfo> unintall_nodes = event.getSource().findAccessibilityNodeInfosByText("安装");
                if (unintall_nodes != null && !unintall_nodes.isEmpty()) {
                    AccessibilityNodeInfo node;
                    for (int i = 0; i < unintall_nodes.size(); i++) {
                        node = unintall_nodes.get(i);
                        if ("android.widget.Button".equals(node.getClassName()) && node.isEnabled()) {
                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }
                }

                List<AccessibilityNodeInfo> next_nodes = event.getSource().findAccessibilityNodeInfosByText("下一步");
                if (next_nodes != null && !next_nodes.isEmpty()) {
                    AccessibilityNodeInfo node;
                    for (int i = 0; i < next_nodes.size(); i++) {
                        node = next_nodes.get(i);
                        if ("android.widget.Button".equals(node.getClassName()) && node.isEnabled()) {
                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }
                }


                int childCount = event.getSource().getChildCount();
                Log.e(TAG,"执行打开"+childCount);
                List<AccessibilityNodeInfo> ok_nodes = event.getSource().findAccessibilityNodeInfosByText("打开");
               if (ok_nodes!=null && !ok_nodes.isEmpty()) {
                    AccessibilityNodeInfo node;
                    for(int i=0; i<ok_nodes.size(); i++){
                        node = ok_nodes.get(i);
                        if ("android.widget.Button".equals(node.getClassName()) && node.isEnabled()) {
                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            Log.e(TAG,"执行打开222222");

                        }
                    }
                }else {
                    Log.e(TAG,"执行打开Fail...");

                }


            }
        }

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void openApk(Event event) {
        if (event ==null) {
            Log.e(TAG, "event is null");
            return;
        }

        if (mEvent == null) {
            Log.e(TAG, "mEvent is null");
            return;
        }
        Log.e(TAG, event.toString());
        List<AccessibilityNodeInfo> ok_nodes = mEvent.getSource().findAccessibilityNodeInfosByText("打开");
        if (ok_nodes != null && !ok_nodes.isEmpty()) {
            AccessibilityNodeInfo node;
            for (int i = 0; i < ok_nodes.size(); i++) {
                node = ok_nodes.get(i);
                if ("android.widget.Button".equals(node.getClassName()) && node.isEnabled()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
    }

    private void processKillApplication(AccessibilityEvent event) {

        if (event.getSource() != null) {
            if (event.getPackageName().equals("com.android.settings")) {
                List<AccessibilityNodeInfo> stop_nodes = event.getSource().findAccessibilityNodeInfosByText("强行停止");
                if (stop_nodes != null && !stop_nodes.isEmpty()) {
                    AccessibilityNodeInfo node;
                    for (int i = 0; i < stop_nodes.size(); i++) {
                        node = stop_nodes.get(i);
                        if (node.getClassName().equals("android.widget.Button")) {
                            if (node.isEnabled()) {
                                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }
                }

                List<AccessibilityNodeInfo> ok_nodes = event.getSource().findAccessibilityNodeInfosByText("确定");
                if (ok_nodes != null && !ok_nodes.isEmpty()) {
                    AccessibilityNodeInfo node;
                    for (int i = 0; i < ok_nodes.size(); i++) {
                        node = ok_nodes.get(i);
                        if (node.getClassName().equals("android.widget.Button")) {
                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            Log.d("action", "click ok");
                        }
                    }

                }
            }
        }
    }


//    @Override
//    public void onAccessibilityEvent(AccessibilityEvent event) {
//        AccessibilityNodeInfo nodeInfo = event.getSource();
//        if (nodeInfo != null && MainActivity.flag) {
//            int eventType = event.getEventType();
//            if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED || eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//                if (handleMap.get(event.getWindowId()) == null) {
//                    boolean handled = iterateNodesAndHandle(nodeInfo);
//                    if (handled) {
//                        handleMap.put(event.getWindowId(), true);
//                    }
//                }
//            }
//
//        }
//    }
//
//    @Override
//    public void onInterrupt() {
//
//    }
//
//    //遍历节点，模拟点击安装按钮
//    private boolean iterateNodesAndHandle(AccessibilityNodeInfo nodeInfo) {
//        if (nodeInfo != null) {
//            int childCount = nodeInfo.getChildCount();
//            if ("android.widget.Button".equals(nodeInfo.getClassName())) {
//                String nodeCotent = nodeInfo.getText().toString();
//                Log.d(TAG, "content is: " + nodeCotent);
//                if ("安装".equals(nodeCotent) || "完成".equals(nodeCotent) || "确定".equals(nodeCotent)) {
//                    nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                    return true;
//                }
//            }
//            //遇到ScrollView的时候模拟滑动一下
//            else if ("android.widget.ScrollView".equals(nodeInfo.getClassName())) {
//                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
//            }
//            for (int i = 0; i < childCount; i++) {
//                AccessibilityNodeInfo childNodeInfo = nodeInfo.getChild(i);
//                if (iterateNodesAndHandle(childNodeInfo)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
