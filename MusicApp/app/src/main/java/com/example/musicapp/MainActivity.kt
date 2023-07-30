package com.example.musicapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        requirePermission()
    }

    interface PermissionRequestDone {
        fun onAccept()
    }

    private val permissionRequestDone = object : PermissionRequestDone {
        override fun onAccept() {
            viewModel.getAllMusic().observe(this@MainActivity){ listSong ->
                listSong.forEach {song ->
                    Log.d("PhucDVb", "onAccept: " + song.title)
                }
            }
        }
    }

    fun requirePermission() : Boolean {
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED){
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 999)
            return false
        }
        permissionRequestDone.onAccept()
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 999){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
                permissionRequestDone.onAccept()
            }
        }
    }
}