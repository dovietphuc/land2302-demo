package com.example.githubusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.githubusers.model.User
import com.example.githubusers.repo.GitHubRepository
import com.example.githubusers.source.GithubUserPagingSource

class MainActivityViewModel : ViewModel() {
    private val pager = Pager<Long, User>(
        config = PagingConfig(20)
    ) {
        GithubUserPagingSource()
    }
    val flowable = pager.flow.cachedIn(viewModelScope)

    private val gitHubRepository: GitHubRepository = GitHubRepository()

    public fun getAllUsers(): LiveData<List<User>> {
        return gitHubRepository.getAllUsers()
    }
}