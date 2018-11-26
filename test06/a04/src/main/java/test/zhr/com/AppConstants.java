package test.zhr.com;

import android.os.Environment;

/**
 * Created by zhr on 2018/4/20.
 * Located:zmkj
 * Des:常量配置项
 */
public class AppConstants {


    /**
     * ping百度，判断外网网络服务器连接
     */
    public static final String BAIDU_URL = "https://www.baidu.com";

    /**
     * ping筑美，判断内网网络服务器连接
     */
    public static final String ZHUMEI_URL = "http://n.zhumei.net/";

    /**
     * 保活服务包名
     */
    public static final String KEEP_ALIVE_SERVICE = "com.zhumei.advscreen.ui.base.service.KeepAliveService";

    /**
     * null
     */
    public static final String NULL_STR = "null";

    /**
     * 默认的退出密码
     */
    public static final String DEFAULT_PASSWORD = "111111";

    /**
     * 默认的公网ip地址
     */
    public static final String DEFAULT_PUBLIC_IP = "0.0.0.0";

    /**
     * 网络超时时间
     */
    public static final int CONNECT_TIMEOUT_LONG = 10 * 1000;
    public static final int CONNECT_TIMEOUT_SHORT = 4 * 1000;

    /**
     * ping百度的频率20秒
     */
    public static final int PING_BAIDU = 20 * 1000;

    /**
     * 每隔多长时间检测一次应用程序是否在后台 2分钟
     */
    public static final int KEEP_ALIVE_CYCLE = 2 * 60 * 1000;

    /**
     * 每隔多长时间从LoginActivity跳到其他Activity 15分钟
     */
    public static final int LOGIN_JUMP_CYCLE = 15 * 60 * 1000;

    /**
     * 十分钟之内的时间进行升级
     */
    public static final int UPDATE_IN_HALF_HOUR = 10 * 60 * 1000;

    /**
     * 商户营业执照等图片每隔几秒钟切换下一张广告
     */
    public static final int BANNER_DELAY_TIME = 5 * 1000;

    /**
     * 登录的配置参数
     */
    public static final int MARKET_INFO_CONFIG_NUM_1 = 0;
    public static final int GUIDE_PRICE_CONFIG_NUM = 1;
    public static final int DETECTION_DATA_CONFIG_NUM = 2;
    public static final int CAROUSEL_DATA_CONFIG_NUM = 3;
    public static final int MARKET_INFO_CONFIG_NUM_2 = 5;

    /**
     * 升级所需的设备类型参数
     */
    public static final String DEVICE_TYPE_COMMERCIAL_SCREEN = "1";
    public static final String DEVICE_TYPE_ADV_SCREEN = "3";

    /**
     * ViewPager展示View的类型
     */
    public static final int VIEWPAGER_IMAGE = 0;
    public static final int VIEWPAGER_VIDEO = 1;

    /**
     * 展示或隐藏View
     */
    public static final String HIDE_VIEW = "0";
    public static final String SHOW_VIEW = "1";

    /**
     * 看电视的默认频道---浙江频道
     */
    public static final int DEFAULT_CHANNEL_NUM = 106;

    /**
     * 默认图片的播放时长
     */
    public static final int DEFAULT_PIC_DURATION = 5 * 1000;

    /**
     * 默认视频的播放时长
     */
    public static final int DEFAULT_VIDEO_DURATION = 60 * 1000;

    /**
     * 视频图片轮播 最大缓存文件数量
     */
    public static final int VIDEO_MAX_DISK_CACHE_FILES_COUNT = 50;

    /**
     * 视频图片轮播 最大缓存大小
     */
    public static final long VIDEO_MAX_DISK_CACHE_SIZE = 2L * 1024L * 1024L * 1024L;

    /**
     * 字符串 最大内存缓存大小
     */
    public static final long STRING_MAX_MEMORY_CACHE_SIZE = Runtime.getRuntime().maxMemory() / 16;

    /**
     * 字符串硬盘缓存标识
     */
    public static final String DISK_CACHE_STR = "DISK_CACHE_STR";

    /**
     * 图片硬盘缓存标识
     */
    public static final String DISK_CACHE_IMAGE = "DISK_CACHE_IMAGE";

    /**
     * 视频硬盘缓存标识
     */
    public static final String DISK_CACHE_VIDEO = "DISK_CACHE_VIDEO";

    /**
     * 根目录下的筑美目录
     */
    public static final String ZHUMEI_ADVSCREEN = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zhumei_adv";

    /**
     * 区分事件类型码
     */
    public static final class EventCode{

        public static final int GET_LOGO_TITLE_FAIL = 0x0001;
        public static final int GET_LOGO_TITLE_SUCCESS = 0x0002;
        public static final int GET_PASSWORD_FAIL = 0x0003;
        public static final int GET_PASSWORD_SUCCESS = 0x0004;
        public static final int GET_DEVICE_ID_FAIL = 0x0005;
        public static final int GET_DEVICE_ID_SUCCESS = 0x0006;
        public static final int GET_MARKET_NUM_FAIL = 0x0007;
        public static final int GET_MARKET_NUM_SUCCESS = 0x0008;
        public static final int GETUI_MSG = 0x0009;
        public static final int RECYCLERVIEW_START_SCROLL = 0x000A;
        public static final int GET_TEMPLATE_CODE_FAIL = 0x000B;
        public static final int GET_TEMPLATE_CODE_SUCCESS = 0x000C;
        public static final int INTERNET_DISCONNECTED4ICON = 0x000D;
        public static final int INTERNET_CONNECTED4ICON = 0x000E;
        public static final int GET_MARKET_INFO_DATA_1_FAIL = 0x000F;
        public static final int GET_MARKET_INFO_DATA_1_SUCCESS = 0x0010;
        public static final int GET_GUIDE_PRICE_DATA_FAIL= 0x0011;
        public static final int GET_GUIDE_PRICE_DATA_SUCCESS= 0x0012;
        public static final int GET_DETECTION_DATA_FAIL = 0x0013;
        public static final int GET_DETECTION_DATA_SUCCESS = 0x0014;
        public static final int GET_PUBLIC_IP_FAIL = 0x0015;
        public static final int GET_PUBLIC_IP_SUCCESS = 0x0016;
        public static final int VIDEO_BANNER = 0x0017;
        public static final int UPDATE_FAIL = 0x0018;
        public static final int UPDATE_SUCCESS = 0x0019;
        public static final int GET_CAROUSEL_DATA_FAIL = 0x001A;
        public static final int GET_CAROUSEL_DATA_SUCCESS = 0x001B;
        public static final int GET_MARKET_INFO_DATA_2_FAIL = 0x001C;
        public static final int GET_MARKET_INFO_DATA_2_SUCCESS = 0x001D;
    }
}
