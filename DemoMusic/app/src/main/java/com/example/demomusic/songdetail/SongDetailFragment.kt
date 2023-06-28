package com.example.demomusic.songdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demomusic.MainActivityViewModel
import com.example.demomusic.R
import com.example.demomusic.databinding.FragmentSongDetailBinding
import phucdv.android.musichelper.Song

class SongDetailFragment() : Fragment(R.layout.fragment_song_detail) {

    lateinit var mBinding: FragmentSongDetailBinding

    lateinit var mActivityViewModel: MainActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentSongDetailBinding.bind(view)
        mActivityViewModel = ViewModelProvider(activity!!).get(MainActivityViewModel::class.java)

        mActivityViewModel.mMusicController.mPlayingSongPos.observe(viewLifecycleOwner, Observer {songPos ->
            if(songPos >= 0) {
                val song: Song = mActivityViewModel.mMusicController.mListSong[songPos]
                mBinding.song = song
            }
        })
    }
}