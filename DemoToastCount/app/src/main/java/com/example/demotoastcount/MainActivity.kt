package com.example.demotoastcount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var mButtonToast: Button
    private lateinit var mButtonCount: Button
    private lateinit var mTextCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mTextCount = findViewById(R.id.tv_count)
        mButtonToast = findViewById(R.id.btn_toast)
        mButtonCount = findViewById(R.id.btn_count)

        mButtonCount.setOnClickListener {
            viewModel.increaseValue()
        }

        mButtonToast.setOnClickListener {
            Toast.makeText(this, viewModel.numberValue.value.toString(), Toast.LENGTH_SHORT).show()
        }

        viewModel.numberValue.observe(this, { number ->
            mTextCount.text = number.toString()
        })
    }
}