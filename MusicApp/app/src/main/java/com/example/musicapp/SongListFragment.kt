package com.example.musicapp

import android.Manifest
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.FragmentSongListBinding

class SongListFragment : Fragment(R.layout.fragment_song_list) {
    lateinit var mSongAdapter: SongListAdapter

    lateinit var mBinding: FragmentSongListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentSongListBinding.bind(view)

        mSongAdapter = SongListAdapter(
            requireContext(),
            ArrayList<Song>(),
            object :
                SongListAdapter.OnSongClickListener {
                override fun onSongClick(pos: Int) {
                    if (pos == musicService!!.mMusicController.mPlayingSongPos.value) {
                        if (musicService!!.mMusicController.isPlaying()) {
                            musicService!!.mMusicController.pause()
                        } else {
                            musicService!!.mMusicController.start()
                        }
                    } else {
                        musicService!!.mMusicController.playSong(pos)
                    }
                    mSongAdapter.notifySongChange(
                        pos,
                        musicService!!.mMusicController.isPlaying()
                    )

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

        mBinding.rcvSong.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rcvSong.adapter = mSongAdapter
        requirePermission()
    }

    interface PermissionRequestDone {
        fun onAccept()
    }

    private var musicService: MusicService? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, binder: IBinder?) {
            musicService = (binder as MusicService.MusicBinder).getService()
            musicService!!.mMusicController.mListSong.observe(viewLifecycleOwner){
                mSongAdapter.mListSong.addAll(it)
                mSongAdapter.notifyDataSetChanged()
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            musicService = null
        }

    }

    private val permissionRequestDone = object : PermissionRequestDone {
        override fun onAccept() {
            val intent = Intent(requireActivity(), MusicService::class.java)
            requireActivity().startForegroundService(intent)
            requireActivity().bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
        }
    }

    private fun requirePermission(): Boolean {

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 999)
            return false
        }
        permissionRequestDone.onAccept()
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

}