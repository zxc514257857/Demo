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
class PlayerPresenter private constructor() {

    private val callbackList = arrayListOf<IPlayerCallback>()
    private var currentPlayState = PlayState.NONE

    // 将PlayerPresenter改成单例模式
    // 1、私有构造方法  -- 不能创建对象
    // 2、创建伴随对象，类似Java中的static  -- 调用时可以类名点调用
    // 3、创建懒加载的一个对象  -- 只创建一个对象
    companion object {
        val instance by lazy {
            PlayerPresenter()
        }
    }


    // 枚举类的写法
    enum class PlayState {
        NONE, PLAYING, PAUSE, LOADING, SWITCH
    }

    fun registerCallback(callback: IPlayerCallback) {
        if (!callbackList.contains(callback)) {
            callbackList.add(callback)
        }
    }

    fun unRegisterCallback(callback: IPlayerCallback) {
        callbackList.remove(callback)
    }

    // 根据状态控制音乐播放或是暂停
    fun playOrPause() {
        dispatchTitleChange("当前播放的歌曲标题")
        dispatchCoverChange("当前播放的歌曲封面")
        // 如果当前不在播放中，就开始播放
        if (currentPlayState != PlayState.PLAYING) {
            dispatchPlayingState()
            currentPlayState = PlayState.PLAYING
        } else {
            dispatchPlayerPauseState()
            currentPlayState = PlayState.PAUSE
        }
    }

    // 播放下一首
    fun playNext() {
        // 1、拿到下一首歌曲 --> 变更UI,包括标题和封面
        dispatchTitleChange("切换到下一首，\r\n标题改变")
        dispatchCoverChange("切换到下一首，\r\n封面改变")
        currentPlayState = PlayState.SWITCH
        // 2、设置给播放器
        // 3、等待播放器的回调通知
    }

    // 播放上一首
    fun playPrevious() {
        dispatchTitleChange("切换到上一首，\r\n标题改变")
        dispatchCoverChange("切换到上一首，\r\n封面改变")
        currentPlayState = PlayState.SWITCH
    }

    private fun dispatchPlayerPauseState() {
        callbackList.forEach {
            it.onPlayerPause()
        }
    }

    private fun dispatchPlayingState() {
        callbackList.forEach {
            it.onPlaying()
        }
    }


    private fun dispatchTitleChange(title: String) {
        callbackList.forEach {
            it.onTitleChange(title)
        }
    }

    private fun dispatchCoverChange(cover: String) {
        callbackList.forEach {
            it.onPlayerCoverChange(cover)
        }
    }
}