package com.example.demomusic

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.util.Size

import android.widget.ImageView

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.io.FileNotFoundException

@BindingAdapter("url")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.baseline_music_note_24)
        .error(R.drawable.ic_launcher_background)
        .into(view)
}

@BindingAdapter("url")
fun setImageUrl(view: ImageView, uri: Uri) {
    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        Glide.with(view)
            .load(uri)
            .centerCrop()
            .placeholder(R.drawable.baseline_music_note_24)
            .error(R.drawable.ic_launcher_background)
            .into(view)
    } else {
        try {
            Glide.with(view)
                .load(
                    view.context.contentResolver
                        .loadThumbnail(uri, Size(500, 500), null)
                )
                .centerCrop()
                .placeholder(R.drawable.baseline_music_note_24)
                .error(R.drawable.ic_launcher_background)
                .into(view)
        } catch (e: FileNotFoundException) {

        }
    }
}