package com.example.githubusers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubusers.databinding.UserItemBinding
import com.example.githubusers.model.User

class UserListAdapter(private var data: List<User> = ArrayList()) : Adapter<UserListAdapter.UserViewHolder>() {

    public fun setData(data: List<User>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class UserViewHolder(val binding: UserItemBinding) : ViewHolder(binding.root){
        public fun bind(user: User){
            binding.txtLogin.text = user.login
            Glide.with(binding.root)
                .load(user.avatarUrl)
                .into(binding.imgAvatar)
        }
    }
}