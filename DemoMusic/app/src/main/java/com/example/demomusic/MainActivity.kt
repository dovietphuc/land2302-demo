package com.example.demomusic

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demomusic.SongListAdapter.OnSongClickListener
import phucdv.android.musichelper.MediaHelper
import phucdv.android.musichelper.Song

class MainActivity : AppCompatActivity() {
    lateinit var mTxtTitle: TextView
    lateinit var mTxtStartDuration: TextView
    lateinit var mTxtEndDuration: TextView
    lateinit var mSeekBar: SeekBar

    lateinit var mBtnPrev: Button
    lateinit var mBtnPlayPause: Button
    lateinit var mBtnNext: Button

    lateinit var mRcvSong: RecyclerView
    lateinit var mSongAdapter: SongListAdapter

    lateinit var mMusicController: MusicController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMusicController = MusicController(this)

        mRcvSong = findViewById(R.id.rcv_song)
        mSongAdapter = SongListAdapter(this, mMusicController.mListSong, object: OnSongClickListener {
            override fun onSongClick(pos: Int) {
                if(pos == mMusicController.mPlayingSongPos){
                    if(mMusicController.isPlaying()){
                        mMusicController.pause()
                    } else {
                        mMusicController.start()
                    }
                } else {
                    mMusicController.playSong(pos)
                    notifySongPlayingChange()
                }
                mSongAdapter.notifySongChange(pos, mMusicController.isPlaying())
            }
        })

        mRcvSong.layoutManager = LinearLayoutManager(this)
        mRcvSong.adapter = mSongAdapter


        mBtnPrev = findViewById(R.id.btn_prev)
        mBtnPlayPause = findViewById(R.id.btn_play_pause)
        mBtnNext = findViewById(R.id.btn_next)

        mTxtTitle = findViewById(R.id.txt_title)
        mTxtStartDuration = findViewById(R.id.txt_start_duration)
        mTxtEndDuration = findViewById(R.id.txt_end_duration)
        mSeekBar = findViewById(R.id.seek_bar)
        mSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    mMusicController.seekTo(seekBar.progress)
                }
            }
        })

        mBtnPlayPause.setOnClickListener {

        }

        mBtnPrev.setOnClickListener {

        }

        mBtnNext.setOnClickListener {

        }

        doRetrieveAllSong()
    }

    fun notifySongPlayingChange() {
        val song = mMusicController.getCurrentPlayingSong()
        if(song != null){
            mTxtTitle.text = song.title
            mTxtStartDuration.text = "00:00"
            mTxtEndDuration.text = song.formatTimes
            mSeekBar.min = 0
            mSeekBar.max = song.millisTimes.toInt()
            mSeekBar.progress = 0
            object : Thread() {
                override fun run() {
                    super.run()
                    while (mMusicController.isPlaying()) {
                        val currentDuration = mMusicController.getCurrentDuration()
                        runOnUiThread {
                            mSeekBar.progress = currentDuration
                            mTxtStartDuration.text = Song.getFormatTimes(currentDuration.toLong())
                            mTxtEndDuration.text =
                                Song.getFormatTimes(song.millisTimes - currentDuration.toLong())
                        }
                        sleep(1000)
                    }
                }
            }.start()
        }

    }

    fun doRetrieveAllSong(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1002)
        } else {
            // Lay danh sach bai hat
            MediaHelper.retrieveAllSong(this) { listSong ->
                mMusicController.mListSong.addAll(listSong)
                mSongAdapter.notifyDataSetChanged()
            }
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