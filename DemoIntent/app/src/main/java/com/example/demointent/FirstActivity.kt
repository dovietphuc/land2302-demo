package com.example.demointent

import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    lateinit var mBtnStartSecondAct: Button

    companion object {
        public val MESSAGE_KEY_EXTRA = "com.example.demointent.extras.MESSAGE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        mBtnStartSecondAct = findViewById(R.id.btn_start_second_act)
        mBtnStartSecondAct.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(MESSAGE_KEY_EXTRA, "Hello second activity")
            val options = ActivityOptions
                .makeSceneTransitionAnimation(this, mBtnStartSecondAct, "test")
            startActivityForResult(intent, 1002, options.toBundle())
        }

        Log.d("PhucDVb", "onCreate: ");
    }

    override fun onStart() {
        super.onStart()
        Log.d("PhucDVb", "onStart: ");
    }

    override fun onResume() {
        super.onResume()
        Log.d("PhucDVb", "onResume: ");
    }

    override fun onPause() {
        super.onPause()
        Log.d("PhucDVb", "onPause: ");
    }

    override fun onStop() {
        super.onStop()
        Log.d("PhucDVb", "onStop: ");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PhucDVb", "onDestroy: ");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("PhucDVb", "onRestart: ");
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if(requestCode == 1002){
                if(data != null){
                    val message = data.getStringExtra(MESSAGE_KEY_EXTRA)
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}