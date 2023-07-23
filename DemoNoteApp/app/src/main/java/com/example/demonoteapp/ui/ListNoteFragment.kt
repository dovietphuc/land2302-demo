package com.example.demonoteapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demonoteapp.R
import com.example.demonoteapp.adapter.NoteListAdapter
import com.example.demonoteapp.databinding.FragmentListNoteBinding
import com.example.demonoteapp.viewModel.ListNoteViewModel

class ListNoteFragment : Fragment(R.layout.fragment_list_note) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentListNoteBinding.bind(view)
        val viewModel = ViewModelProvider(this).get(ListNoteViewModel::class.java)

        val adapter = NoteListAdapter()

        viewModel.listAllNotes.observe(viewLifecycleOwner) { listNotes ->
            adapter.submit(ArrayList(listNotes))
        }

        binding.rcvListNote.layoutManager = LinearLayoutManager(context)
        binding.rcvListNote.adapter = adapter
    }

}