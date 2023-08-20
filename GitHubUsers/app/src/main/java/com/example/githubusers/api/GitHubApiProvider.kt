package com.example.githubusers.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitHubApiProvider {
    companion object {
        private val BASE_URL = "https://api.github.com"
        private var githubApi: GitHubApi? = null
        private var retrofit: Retrofit? = null

        public fun getRetrofit(): Retrofit {
            if(retrofit == null){
                val okHttpClient = OkHttpClient.Builder().build()
                retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        public fun getGitHubApi(): GitHubApi{
            if(githubApi == null){
                githubApi = getRetrofit().create(GitHubApi::class.java)
            }
            return githubApi!!
        }
    }
}