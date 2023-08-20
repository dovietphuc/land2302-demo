package com.example.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.githubusers.adapter.UserListAdapter
import com.example.githubusers.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val userAdapter = UserListAdapter()
        binding.rcvUser.adapter = userAdapter

//        viewModel.getAllUsers().observe(this) {users ->
//            userAdapter.setData(users)
//        }
        lifecycleScope.launch {
            viewModel.flowable.collectLatest { page ->
                userAdapter.submitData(page)
            }
        }
    }
}