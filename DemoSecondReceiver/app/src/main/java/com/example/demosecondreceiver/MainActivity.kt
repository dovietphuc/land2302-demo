package com.example.demosecondreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    public val ACTION_CUSTOM = "com.example.demoreceiver.action.ACTION_CUSTOM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(intent != null){
                    Log.d("PhucDVb", "An cap duoc action: " + intent.action)
                }
            }
        }
        registerReceiver(receiver, IntentFilter(ACTION_CUSTOM))

    }
}