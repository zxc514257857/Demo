package com.zhr.test.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.test.R

class PlayerActivity : AppCompatActivity(), IPlayerCallback {

    private val playerPresenter by lazy {
        PlayerPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerPresenter.registerCallback(this)
        initListener()
    }

    private fun initListener() {

    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }

    override fun onTitleChange(title: String) {
        TODO("Not yet implemented")
    }

    override fun onProgressChange(currentProgress: Int) {
        TODO("Not yet implemented")
    }

    override fun onPlaying() {
        TODO("Not yet implemented")
    }

    override fun onPlayerPause() {
        TODO("Not yet implemented")
    }

    override fun onPlayerCoverChange() {
        TODO("Not yet implemented")
    }
}
