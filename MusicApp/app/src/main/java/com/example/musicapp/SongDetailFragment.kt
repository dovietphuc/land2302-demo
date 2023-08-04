package com.example.musicapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.databinding.ControllerLayoutBinding
import com.example.musicapp.databinding.FragmentSongDetailBinding

class SongDetailFragment() : Fragment(R.layout.fragment_song_detail) {

    lateinit var mBinding: FragmentSongDetailBinding

    lateinit var mActivityViewModel: MainActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentSongDetailBinding.bind(view)
        val controllerBinding = DataBindingUtil.setContentView<ControllerLayoutBinding>(requireActivity(),
            R.layout.controller_layout)
        mActivityViewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
    }
}