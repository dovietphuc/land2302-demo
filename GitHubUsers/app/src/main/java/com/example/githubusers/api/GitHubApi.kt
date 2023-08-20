package com.example.githubusers.api

import com.example.githubusers.model.User
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("/users")
    fun getAllUsersUsingRx(@Query("since") since: Long = 0,
                    @Query("per_page") perPage: Int = 30) : Single<List<User>>

    @GET("/users")
    fun getAllUsers(@Query("since") since: Long = 0,
                    @Query("per_page") perPage: Int = 30) : Call<List<User>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String) : Call<User>
}