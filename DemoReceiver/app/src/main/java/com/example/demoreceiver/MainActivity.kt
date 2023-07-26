package com.example.demoreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private lateinit var demoReceiver: BroadcastReceiver
    private val customReceiver = CustomReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_send_broadcast).setOnClickListener {
            val intent = Intent()
            intent.action = CustomReceiver.ACTION_CUSTOM
            intent.`package` = "com.example.demosecondreceiver"
            sendBroadcast(intent)
        }
        val filter = IntentFilter()
        filter.addAction(CustomReceiver.ACTION_CUSTOM)
        registerReceiver(customReceiver, filter)












        demoReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if(intent != null){
                    when(intent.action){
                        Intent.ACTION_POWER_CONNECTED ->
                            findViewById<ConstraintLayout>(R.id.root).setBackgroundColor(Color.GREEN)
                        Intent.ACTION_POWER_DISCONNECTED ->
                            findViewById<ConstraintLayout>(R.id.root).setBackgroundColor(Color.RED)
                    }
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        registerReceiver(demoReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(demoReceiver)
        unregisterReceiver(customReceiver)
    }
}