package com.example.demomusic.songlist

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomusic.MainActivityViewModel
import com.example.demomusic.MusicController
import com.example.demomusic.R
import com.example.demomusic.SongListAdapter
import com.example.demomusic.databinding.FragmentSongListBinding
import com.example.demomusic.songdetail.SongDetailFragment
import phucdv.android.musichelper.MediaHelper

class SongListFragment : Fragment(R.layout.fragment_song_list) {
    lateinit var mSongAdapter: SongListAdapter

    lateinit var mBinding: FragmentSongListBinding

    lateinit var mActivityViewModel: MainActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentSongListBinding.bind(view)

        mActivityViewModel = ViewModelProvider(activity!!).get(MainActivityViewModel::class.java)

        mActivityViewModel.mMusicController = MusicController(context!!)

        mSongAdapter = SongListAdapter(context!!, mActivityViewModel.mMusicController.mListSong, object:
            SongListAdapter.OnSongClickListener {
            override fun onSongClick(pos: Int) {
                if(pos == mActivityViewModel.mMusicController.mPlayingSongPos.value){
                    if(mActivityViewModel.mMusicController.isPlaying()){
                        mActivityViewModel.mMusicController.pause()
                    } else {
                        mActivityViewModel.mMusicController.start()
                    }
                } else {
                    mActivityViewModel.mMusicController.playSong(pos)
                }
                mSongAdapter.notifySongChange(pos, mActivityViewModel.mMusicController.isPlaying())

                val currentOrientation = resources.configuration.orientation
                if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    // Landscape
                } else {
                    // Portrait
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.host_fragment, SongDetailFragment::class.java, null)
                        .addToBackStack("song_detail")
                        .commit()
                }
            }
        })

        mBinding.rcvSong.layoutManager = LinearLayoutManager(context!!)
        mBinding.rcvSong.adapter = mSongAdapter

        doRetrieveAllSong()
    }

    fun doRetrieveAllSong(){
        if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1002)
        } else {
            // Lay danh sach bai hat
            MediaHelper.retrieveAllSong(context!!) { listSong ->
                mActivityViewModel.mMusicController.mListSong.addAll(listSong)
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
        if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED){
            doRetrieveAllSong()
        }
    }

}