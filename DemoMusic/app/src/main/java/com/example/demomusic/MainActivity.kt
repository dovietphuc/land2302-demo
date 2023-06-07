package com.example.demomusic

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import phucdv.android.musichelper.MediaHelper
import phucdv.android.musichelper.Song


class MainActivity : AppCompatActivity() {
    lateinit var mTxtSongName: TextView
    lateinit var mBtnPrev: Button
    lateinit var mBtnPlayPause: Button
    lateinit var mBtnNext: Button
    val mListSong = ArrayList<Song>()
    var mPlayingSongPos = -1

    val mMediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTxtSongName = findViewById(R.id.txt_song_name)
        mBtnPrev = findViewById(R.id.btn_prev)
        mBtnPlayPause = findViewById(R.id.btn_play_pause)
        mBtnNext = findViewById(R.id.btn_next)

        mBtnPlayPause.setOnClickListener {
            if(mMediaPlayer.isPlaying){
                mMediaPlayer.pause()
            } else {
                var isFirstTime = false
                if(mPlayingSongPos == -1){
                    mPlayingSongPos = 0
                    isFirstTime = true
                }
                if (mPlayingSongPos >= 0 && mPlayingSongPos < mListSong.size && isFirstTime) {
                    playSong(this, mListSong[mPlayingSongPos].id)
                } else {
                    mMediaPlayer.start()
                }
            }
        }

        mBtnPrev.setOnClickListener {
            playPrev()
        }

        mBtnNext.setOnClickListener {
            playNext()
        }

        mMediaPlayer.setOnPreparedListener {
            mMediaPlayer.start()
            mTxtSongName.text = mListSong[mPlayingSongPos].title
        }

        doRetrieveAllSong()
    }

    fun playSong(context: Context?, songId: Long) {
        mMediaPlayer.reset()
        val trackUri =
            ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId)
        try {
            mMediaPlayer.setDataSource(context!!, trackUri)
        } catch (e: Exception) {
            Log.e("MUSIC SERVICE", "Error starting data source", e)
        }
        mMediaPlayer.prepareAsync()
    }

    fun playNext(){
        mPlayingSongPos++
        if(mPlayingSongPos >= mListSong.size){
            mPlayingSongPos = 0
        }
        playSong(this, mListSong[mPlayingSongPos].id)
    }

    fun playPrev(){
        mPlayingSongPos--
        if(mPlayingSongPos < 0){
            mPlayingSongPos = mListSong.size - 1
        }
        playSong(this, mListSong[mPlayingSongPos].id)
    }

    fun doRetrieveAllSong(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1002)
        } else {
            // Lay danh sach bai hat
            MediaHelper.retrieveAllSong(this, { listSong ->
                mListSong.addAll(listSong)
            })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED){
            doRetrieveAllSong()
        }
    }
}