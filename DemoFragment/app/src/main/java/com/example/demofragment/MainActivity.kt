package com.example.demofragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            val bundle = Bundle()
            bundle.putString("MESSAGE_KEY", "This is param using bundle")
            supportFragmentManager.beginTransaction()
                .add(R.id.host_fragment, DemoFragment::class.java, bundle)
                .commit()
        }
    }
}