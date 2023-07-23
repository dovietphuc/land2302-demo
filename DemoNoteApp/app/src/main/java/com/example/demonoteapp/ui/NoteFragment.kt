package com.example.demonoteapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.demonoteapp.R
import com.example.demonoteapp.database.Note
import com.example.demonoteapp.databinding.FragmentNoteBinding
import com.example.demonoteapp.viewModel.NoteViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class NoteFragment : Fragment(R.layout.fragment_note) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNoteBinding.bind(view)
        val viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.btnSave.setOnClickListener {
            val note = Note(binding.edtTitle.text.toString(), "", System.currentTimeMillis())
            lifecycleScope.launch {
                viewModel.insert(note)
                Toast.makeText(context, "Save success", Toast.LENGTH_SHORT).show()
            }
        }
    }
}