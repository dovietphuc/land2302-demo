package com.example.demoreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DemoReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null){
            val action = intent.action
            when(action){
                Intent.ACTION_TIMEZONE_CHANGED ->
                    Log.d("PhucDVb", "ACTION_TIMEZONE_CHANGED")
            }
        }
    }
}