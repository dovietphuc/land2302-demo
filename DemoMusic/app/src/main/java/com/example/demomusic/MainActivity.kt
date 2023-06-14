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
import com.example.demomusic.databinding.ActivityMainBinding
import phucdv.android.musichelper.MediaHelper
import phucdv.android.musichelper.Song

class MainActivity : AppCompatActivity() {
    lateinit var mSongAdapter: SongListAdapter

    lateinit var mMusicController: MusicController

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mMusicController = MusicController(this)

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

        mBinding.rcvSong.layoutManager = LinearLayoutManager(this)
        mBinding.rcvSong.adapter = mSongAdapter

        mBinding.controller.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    mMusicController.seekTo(seekBar.progress)
                }
            }
        })

        mBinding.controller.btnPlayPause.setOnClickListener {

        }

        mBinding.controller.btnPrev.setOnClickListener {

        }

        mBinding.controller.btnNext.setOnClickListener {

        }

        doRetrieveAllSong()
    }

    fun notifySongPlayingChange() {
        val song = mMusicController.getCurrentPlayingSong()
        if(song != null) {
            mBinding.controller.apply {
                this.song = song
                txtStartDuration.text = "00:00"
                txtEndDuration.text = song.formatTimes
                seekBar.max = song.millisTimes.toInt()
                seekBar.progress = 0
            }
            object : Thread() {
                override fun run() {
                    super.run()
                    while (mMusicController.isPlaying()) {
                        val song = mMusicController.getCurrentPlayingSong()
                        val currentDuration = mMusicController.getCurrentDuration()
                        runOnUiThread {
                            mBinding.controller.apply {
                                seekBar.progress = currentDuration
                                txtStartDuration.text = Song.getFormatTimes(currentDuration.toLong())
                                txtEndDuration.text =
                                    Song.getFormatTimes(song!!.millisTimes - currentDuration.toLong())
                            }
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