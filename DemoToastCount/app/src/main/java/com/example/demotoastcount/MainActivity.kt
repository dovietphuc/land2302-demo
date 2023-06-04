package com.example.demotoastcount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var mButtonToast: Button
    private lateinit var mButtonCount: Button
    private lateinit var mTextCount: TextView

    private var mValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            mValue = savedInstanceState.getInt("COUNT_KEY", 0)
        }

        mTextCount = findViewById(R.id.tv_count)
        mButtonToast = findViewById(R.id.btn_toast)
        mButtonCount = findViewById(R.id.btn_count)

        mTextCount.text = mValue.toString()

        mButtonCount.setOnClickListener {
            mValue++
            mTextCount.text = mValue.toString()
        }

        mButtonToast.setOnClickListener {
            Toast.makeText(this, mValue.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("COUNT_KEY", mValue)
    }
}