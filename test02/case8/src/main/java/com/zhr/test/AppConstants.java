package com.zhr.test;

public class AppConstants {

    /**
     * 视频 最大缓存文件数量 50个
     */
    public static final int VIDEO_MAX_DISK_CACHE_FILES_COUNT = 50;

    /**
     * 视频 最大硬盘缓存大小 2G
     */
    public static final long VIDEO_MAX_DISK_CACHE_SIZE = 2L * 1024L * 1024L * 1024L;

    /**
     * 图片 最大硬盘缓存大小 500M
     */
    public static final long IMAGE_MAX_DISK_CACHE_SIZE = 500L * 1024L * 1024L;

    /**
     * 图片 最大内存缓存大小
     */
    public static final long IMAGE_MAX_MEMORY_CACHE_SIZE = Runtime.getRuntime().maxMemory() / 8;

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

}
