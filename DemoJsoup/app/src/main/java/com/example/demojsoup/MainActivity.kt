package com.example.demojsoup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class MainActivity : AppCompatActivity() {

    companion object {
        val BASE_VOZ_URL = "https://voz.vn"
        val VOZ_URL = "${BASE_VOZ_URL}/s/chia-se-kien-thuc.104"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            val document = getDocument(VOZ_URL)
            val elements = document.select(".structItem-title a")
            elements.forEach { e ->
                Log.d("PhucDVb", e.text())
                Log.d("PhucDVb", BASE_VOZ_URL + e.attr("href"))
            }
        }
    }

    suspend fun getDocument(url: String): Document {
        return withContext(Dispatchers.IO) {
            return@withContext Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .get()
        }
    }
}