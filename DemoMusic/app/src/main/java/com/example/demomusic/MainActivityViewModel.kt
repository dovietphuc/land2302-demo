package com.example.demomusic

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var mMusicController: MusicController

    init {
        mMusicController = MusicController(application.applicationContext)
    }
}