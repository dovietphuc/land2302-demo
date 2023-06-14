package com.example.demomusic

import android.graphics.drawable.Drawable

import android.widget.ImageView

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.baseline_music_note_24)
        .error(R.drawable.ic_launcher_background)
        .into(view)
}

