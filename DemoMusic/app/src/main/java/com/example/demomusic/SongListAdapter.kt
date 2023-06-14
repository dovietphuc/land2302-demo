package com.example.demomusic

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.MediaStore.Audio
import android.provider.MediaStore.Images.Media
import android.provider.MediaStore.Images.Thumbnails
import android.service.controls.templates.ThumbnailTemplate
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.demomusic.databinding.SongItemBinding
import phucdv.android.musichelper.Song
import java.net.URI
import java.net.URL

class SongListAdapter(val mContext: Context, val mListSong: List<Song>, val onSongClickListener: OnSongClickListener) : Adapter<SongListAdapter.SongViewHolder>() {

    var mPlayingPos = -1
    var mIsPlaying = false

    interface OnSongClickListener {
        fun onSongClick(pos: Int)
    }

    fun notifySongChange(playingPos: Int, isPlaying: Boolean) {
        val oldPos = mPlayingPos
        mIsPlaying = isPlaying
        mPlayingPos = playingPos
        notifyItemChanged(oldPos)
        notifyItemChanged(mPlayingPos)
    }

    inner class SongViewHolder(itemView: View) : ViewHolder(itemView){
        val mBinding = SongItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                onSongClickListener.onSongClick(adapterPosition)
            }
        }

        fun bind(song: Song){

            mBinding.song = song
            mBinding.imageUrl = "https://galaxylands.com.vn/wp-content/uploads/2022/12/thong-tin-tieu-su-ca-si-bich-phuong-noi-tieng-showbiz-viet-5.jpg"

            if(adapterPosition == mPlayingPos){
                if(mIsPlaying){
                    itemView.setBackgroundColor(Color.GREEN)
                } else {
                    itemView.setBackgroundColor(Color.YELLOW)
                }
            } else {
                itemView.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.song_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mListSong.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(mListSong[position])
    }
}