package com.example.githubusers.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("login")
    @Expose
    val login: String,
    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String
)