package com.example.demomusic

import android.content.ContentUris
import android.content.Context
import android.media.MediaPlayer
import android.provider.MediaStore
import android.util.Log
import phucdv.android.musichelper.Song

class MusicController(val mContext: Context) {
    private val mMediaPlayer = MediaPlayer()
    var mPlayingSongPos = -1
    val mListSong = ArrayList<Song>()
    private var mIsPreparing = false

    init {
        mMediaPlayer.setOnPreparedListener {
            mMediaPlayer.start()
            mIsPreparing = false
        }
    }

    fun seekTo(pos: Int){
        mMediaPlayer.seekTo(pos)
    }

    fun getCurrentDuration(): Int{
        return mMediaPlayer.currentPosition
    }

    fun getCurrentPlayingSong() : Song? {
        if(mPlayingSongPos < 0 || mPlayingSongPos >= mListSong.size)
            return null
        return mListSong[mPlayingSongPos]
    }

    fun isPlaying(): Boolean {
        return mMediaPlayer.isPlaying || mIsPreparing
    }

    fun pause() {
        mMediaPlayer.pause()
    }

    fun start(){
        mMediaPlayer.start()
    }

    fun playSong(pos: Int) {
        mPlayingSongPos = pos
        val songId = mListSong[mPlayingSongPos].id
        mMediaPlayer.reset()
        val trackUri =
            ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId)
        try {
            mMediaPlayer.setDataSource(mContext, trackUri)
        } catch (e: Exception) {
            Log.e("MUSIC SERVICE", "Error starting data source", e)
        }
        mMediaPlayer.prepareAsync()
        mIsPreparing = true
    }

    fun playNext(){
        mPlayingSongPos++
        if(mPlayingSongPos >= mListSong.size){
            mPlayingSongPos = 0
        }
        playSong(mPlayingSongPos)
    }

    fun playPrev(){
        mPlayingSongPos--
        if(mPlayingSongPos < 0){
            mPlayingSongPos = mListSong.size - 1
        }
        playSong(mPlayingSongPos)
    }
}