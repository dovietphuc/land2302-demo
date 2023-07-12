package com.example.democoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

sealed class Results<out T> {
    data class Success<out T>(val data: T): Results<T>()
    data class Error(val e: Exception): Results<Nothing>()
}

class DemoRepository {
    suspend fun doLongTask() : Results<String>{
        return withContext(Dispatchers.IO){
            Thread.sleep(5000)
            return@withContext Results.Success<String>(System.currentTimeMillis().toString())
        }
    }
}