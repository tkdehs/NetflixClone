package com.pnxand.netflixclone.view.core.adapter

import android.content.Context
import android.icu.text.CaseMap
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.databinding.FragmentTitleRowBinding
import com.pnxand.netflixclone.model.Title

class TvAdapter(val context:Context) :ListAdapter<Title,RecyclerView.ViewHolder>(DiffUtilItemCallback){
    val TYPE_HEADER = 0
    val TYPE_ITEM = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_HEADER -> {
                TitleHolder(TextView(context))
            }
            else ->{
                ItemHolder(FragmentTitleRowBinding.inflate(LayoutInflater.from(context)))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TitleHolder->{
                holder.bind()
            }
            is ItemHolder->{
                holder.bind(getItem(position-1))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0->{
                TYPE_HEADER
            }
            else->{
                TYPE_ITEM
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    inner class TitleHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
        fun bind(){
            textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100)
            textView.setPadding(20,0,0,0)
            textView.text = "Upcoming"
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,19f)
            textView.gravity = Gravity.CENTER_VERTICAL
        }
    }

    inner class ItemHolder(val binding: FragmentTitleRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:Title){
            val url = "https://image.tmdb.org/t/p/w500/${data.poster_path}"
            Log.d("SectionAdapter","Title:${data.original_name}, url : $url")
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.heroimage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivImg)

            binding.tvTitle.text = if(data.original_name != ""){
                data.original_name
            } else {
                data.original_title
            }
        }
    }

    object DiffUtilItemCallback : DiffUtil.ItemCallback<Title>() {
        override fun areItemsTheSame(oldItem: Title, newItem: Title) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Title, newItem: Title) = true
    }
}