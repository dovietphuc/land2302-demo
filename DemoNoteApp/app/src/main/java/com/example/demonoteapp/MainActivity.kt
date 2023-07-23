package com.example.demonoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demonoteapp.databinding.ActivityMainBinding
import com.example.demonoteapp.ui.NoteFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddNote.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.host_fragment, NoteFragment::class.java, null)
                .addToBackStack("note_fragment")
                .commit()
        }
    }
}