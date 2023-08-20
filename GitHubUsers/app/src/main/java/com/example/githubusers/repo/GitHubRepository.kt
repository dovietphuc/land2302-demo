package com.example.githubusers.repo

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubusers.api.GitHubApi
import com.example.githubusers.api.GitHubApiProvider
import com.example.githubusers.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepository {
    private val githubApi: GitHubApi = GitHubApiProvider.getGitHubApi()

    public fun getAllUsers(): LiveData<List<User>> {
        val users = MutableLiveData<List<User>>(ArrayList<User>())

        githubApi.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    users.postValue(response.body())
                } else {
                    Log.e("GitHubRepository", "onResponse: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return users
    }
}