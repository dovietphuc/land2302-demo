package com.example.democoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            lifecycleScope.launch {
                Log.d("PhucDVb", "Result: " + doTask())
            }
        }
    }

    suspend fun doTask() : String{
        Log.d("PhucDVb", "Start do some task take a long time: ");
        var result = "Null data"
        val demoRepository = DemoRepository()
        result = (demoRepository.doLongTask() as Results.Success<String>).data
        return result
    }
}