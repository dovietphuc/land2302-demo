package com.example.demointent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var mTxtMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        mTxtMessage = findViewById(R.id.txt_message)

        val message = intent.getStringExtra(FirstActivity.MESSAGE_KEY_EXTRA)
        mTxtMessage.text = message

        mTxtMessage.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(FirstActivity.MESSAGE_KEY_EXTRA, "This is reply message")
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}