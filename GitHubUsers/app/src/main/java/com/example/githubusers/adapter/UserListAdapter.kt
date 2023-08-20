package com.example.githubusers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubusers.databinding.UserItemBinding
import com.example.githubusers.model.User

class UserListAdapter
    : PagingDataAdapter<User, UserListAdapter.UserViewHolder>(
        object: DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return areItemsTheSame(oldItem, newItem)
            }
        }
    ) {

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
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(val binding: UserItemBinding) : ViewHolder(binding.root){
        public fun bind(user: User?){
            if(user != null) {
                binding.txtLogin.text = "${user.id}. ${user.login}"
                Glide.with(binding.root)
                    .load(user.avatarUrl)
                    .into(binding.imgAvatar)
            }
        }
    }
}