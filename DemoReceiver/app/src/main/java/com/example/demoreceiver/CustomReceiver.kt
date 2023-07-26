package com.example.demoreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class CustomReceiver : BroadcastReceiver() {
    companion object {
        public val ACTION_CUSTOM = "com.example.demoreceiver.action.ACTION_CUSTOM"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null){
            when(intent.action){
                ACTION_CUSTOM ->
                    Log.d("PhucDVb", "onReceive: ACTION_CUSTOM")
            }
        }
    }
}