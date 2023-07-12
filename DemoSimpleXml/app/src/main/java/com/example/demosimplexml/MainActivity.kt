package com.example.demosimplexml

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.coroutineContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = assets.open("hocsinhdata.xml")
        val hocSinhs = parser(inputStream)
        for (hocSinh in hocSinhs) {
            Log.d("PhucDVb", "Hoc sinh: " + hocSinh)
        }

        lifecycleScope.launch {
            val input = downloadUrl("https://stackoverflow.com/feeds/tag?" +
                    "tagnames=android&sort=newest")
        }
    }

    suspend fun downloadUrl(urlString: String): InputStream {
        return withContext(Dispatchers.IO) {
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 10000
            conn.connectTimeout = 15000
            conn.requestMethod = "GET"
            conn.doInput = true
            // Starts the query
            conn.connect()
            val input = conn.inputStream
            Log.d("PhucDVb", ": " + String(input.readBytes()));
            return@withContext input
        }
    }

}