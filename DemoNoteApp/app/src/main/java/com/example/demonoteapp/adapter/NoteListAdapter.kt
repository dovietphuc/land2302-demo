package com.example.demonoteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.demonoteapp.database.Note
import com.example.demonoteapp.databinding.NoteItemBinding

class NoteListAdapter : Adapter<NoteListAdapter.NoteViewHolder>() {

    private var data = ArrayList<Note>()

    fun submit(data: ArrayList<Note>){
        this.data = data
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(val binding: NoteItemBinding): ViewHolder(binding.root) {
        fun bind(note: Note){
            binding.txtId.text = note.id.toString()
            binding.txtTitle.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(data[position])
    }
}