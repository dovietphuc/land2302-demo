package com.example.demonavigationfragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.demonavigationfragment.databinding.StudentItemBinding

class StudentAdapter(
    private val values: List<String>
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            StudentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.txtName.text = item
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: StudentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val txtName: TextView = binding.txtName
    }

}