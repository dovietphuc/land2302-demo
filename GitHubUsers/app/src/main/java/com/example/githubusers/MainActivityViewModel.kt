package com.example.githubusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.model.User
import com.example.githubusers.repo.GitHubRepository

class MainActivityViewModel : ViewModel() {
    private val gitHubRepository: GitHubRepository = GitHubRepository()

    public fun getAllUsers(): LiveData<List<User>> {
        return gitHubRepository.getAllUsers()
    }
}