package com.zhr.test.player

/**
 * 播放音乐
 * 暂停音乐
 * 上一首
 * 下一首
 * =======================
 * 播放的状态：
 * - 通知UI改变成播放状态 && 通知UI进度的变化
 * 上一首，下一首
 * - 通知UI歌曲标题变化 && 通知UI歌曲封面变化 && 通知UI进度的变化
 * 暂停音乐
 * - 更新UI状态为暂停
 */
class PlayerPresenter {

    private val callbackList = arrayListOf<IPlayerCallback>()

    fun registerCallback(callback: IPlayerCallback) {
        if (!callbackList.contains(callback)) {
            callbackList.add(callback)
        }
    }

    fun unRegisterCallback(callback: IPlayerCallback) {
        callbackList.remove(callback)
    }
}