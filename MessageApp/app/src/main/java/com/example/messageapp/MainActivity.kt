package com.example.messageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_host, HomeFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_host, LoginFragment())
                .commit()
        }
    }
}