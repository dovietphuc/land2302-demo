package com.example.githubusers.api

import com.example.githubusers.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("/users")
    fun getAllUsers() : Call<List<User>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String) : Call<User>
}