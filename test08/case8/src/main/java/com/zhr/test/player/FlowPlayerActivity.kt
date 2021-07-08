package com.zhr.test.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.test.R
import kotlinx.android.synthetic.main.activity_flow_player.*

class FlowPlayerActivity : AppCompatActivity(), IPlayerCallback {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player)
        playerPresenter.registerCallback(this)
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }

    private fun initListener() {
        btnPlayOrPause.setOnClickListener {
            playerPresenter.playOrPause()
        }
    }

    override fun onTitleChange(title: String) {
        TODO("Not yet implemented")
    }

    override fun onProgressChange(currentProgress: Int) {
        TODO("Not yet implemented")
    }

    // 这个类中的情况，我只是想要改变按钮的状态，其他的接口中的方法不用去实现，因为也用不上，出现了多余的情况
    override fun onPlaying() {
        btnPlayOrPause.text = "暂停"
    }

    override fun onPlayerPause() {
        btnPlayOrPause.text = "播放"
    }

    override fun onPlayerCoverChange(cover: String) {
        TODO("Not yet implemented")
    }
}