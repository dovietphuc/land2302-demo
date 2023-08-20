package com.example.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.githubusers.adapter.UserListAdapter
import com.example.githubusers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val userAdapter = UserListAdapter()
        binding.rcvUser.adapter = userAdapter

        viewModel.getAllUsers().observe(this) {users ->
            userAdapter.setData(users)
        }
    }
}