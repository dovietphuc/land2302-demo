package com.example.musicapp

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicService : Service() {
    lateinit var mMusicController: MusicController
    lateinit var mMusicRepository: MusicRepository

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        mMusicRepository = MusicRepository(this)
        mMusicController = MusicController(this)
        val ioScope = CoroutineScope(Dispatchers.IO)
        ioScope.launch {
            val listAllMusic = mMusicRepository.getAllMusic()
            mMusicController.mListSong.postValue(listAllMusic)
        }

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(createNotificationChannel())
    }

    fun play(pos: Int){
        mMusicController.playSong(pos)
    }

    fun start(){
        mMusicController.start()
    }

    fun pause(){
        mMusicController.pause()
    }

    fun next(){
        mMusicController.playNext()
    }

    fun previous(){
        mMusicController.playPrev()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent != null) {
            startForeground(999, createNotification())
            when (intent.action) {
                "ACTION_PLAY" -> {
                    val pos = intent.getIntExtra("pos", 0)
                    play(pos)
                }
                "ACTION_START" -> {
                    start()
                }
                "ACTION_PAUSE" -> {
                    pause()
                }
                "ACTION_NEXT" -> {
                    next()
                }
                "ACTION_PREVIOUS" -> {
                    previous()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        val channel =
            NotificationChannel(
                "music_channel",
                "Music playback", NotificationManager.IMPORTANCE_LOW
            )
        return channel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(): Notification {
        val remoteView = RemoteViews(this.packageName, R.layout.music_notification)
        val nextIntent = PendingIntent.getService(
            this,
            999,
            Intent(this, MusicService::class.java).setAction("ACTION_NEXT"),
            PendingIntent.FLAG_IMMUTABLE
        )
        remoteView.setOnClickPendingIntent(R.id.btn_next, nextIntent)

        return Notification.Builder(this, "music_channel")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setCustomContentView(remoteView)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder {
        return MusicBinder()
    }

    inner class MusicBinder : Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }
}