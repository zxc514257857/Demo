package com.zhr.test.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.test.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity(), IPlayerCallback {

    private val playerPresenter by lazy {
        PlayerPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        // 当这里多次调用的时候 就会出现多个callback
        playerPresenter.registerCallback(this)
        initListener()
    }

    private fun initListener() {
        btnPlayOrPause.setOnClickListener {
            playerPresenter.playOrPause()
        }
        btnPlayNext.setOnClickListener {
            playerPresenter.playNext()
        }
        btnPlayPrevious.setOnClickListener {
            playerPresenter.playPrevious()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }

    // P层写回调、写其他方法
    // P层写接口、写方法，然后去实现接口里面的方法
    override fun onTitleChange(title: String) {
        tvSongTitle?.text = title
    }

    override fun onProgressChange(currentProgress: Int) {}

    override fun onPlaying() {
        // 播放时 --> 显示暂停
        btnPlayOrPause?.text = "暂停"
    }

    override fun onPlayerPause() {
        // 暂停时 --> 显示播放
        btnPlayOrPause?.text = "播放"
    }

    override fun onPlayerCoverChange(cover: String) {
        tvSongCover?.text = cover
    }
}
