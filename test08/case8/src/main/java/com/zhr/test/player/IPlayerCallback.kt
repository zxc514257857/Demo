package com.zhr.test.player

interface IPlayerCallback {

    fun onTitleChange(title: String)

    fun onProgressChange(currentProgress: Int)

    fun onPlaying()

    fun onPlayerPause()

    fun onPlayerCoverChange(cover: String)
}