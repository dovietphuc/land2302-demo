package com.example.demointent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            startActivityForResult(intent, 1002)
        }
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