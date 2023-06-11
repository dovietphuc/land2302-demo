package com.example.demomusic

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
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
import phucdv.android.musichelper.Song

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
        val mImageView = itemView.findViewById<ImageView>(R.id.img_art)
        val mTxtTitle = itemView.findViewById<TextView>(R.id.txt_title)
        val mTxtArtist = itemView.findViewById<TextView>(R.id.txt_artist)
        val mTxtDuration = itemView.findViewById<TextView>(R.id.txt_duration)

        init {
            itemView.setOnClickListener {
                onSongClickListener.onSongClick(adapterPosition)
            }
        }

        fun bind(song: Song){
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                mImageView.setImageURI(song.albumUri)
            } else {
//                val albumUri =
//                    ContentUris.withAppendedId(
//                        MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, song.albumUri.lastPathSegment!!.toLong())
//                val albumArt =
//                    mContext.contentResolver.loadThumbnail(albumUri, Size(100, 100), null)
//                mImageView.setImageBitmap(albumArt)
            }


            mTxtTitle.text = song.title
            mTxtArtist.text = song.artist
            mTxtDuration.text = song.formatTimes

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