package com.pnxand.netflixclone.view.core.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.databinding.FragmentHomeBinding
import com.pnxand.netflixclone.databinding.FragmentHomeHeaderBinding
import com.pnxand.netflixclone.databinding.FragmentHomeItemBinding
import com.pnxand.netflixclone.databinding.FragmentHomeItemRowBinding
import com.pnxand.netflixclone.model.Title
import com.pnxand.netflixclone.view.base.BaseFragment
import kotlinx.coroutines.launch

class HomeFragment :BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    lateinit var binding:FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(requireContext()))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setList()
        return binding.root
    }

    fun setList(){
        binding.rvSectionList.adapter = HomeMainAdapter()
        binding.rvSectionList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }

    inner class HomeMainAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        val TYPE_HEADER = 0
        val TYPE_TITLE = 1
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when(viewType) {
                TYPE_HEADER ->{
                    HeaderViewHolder(FragmentHomeHeaderBinding.inflate(LayoutInflater.from(parent.context)))
                }
                else ->{
                    TitleViewHolder(FragmentHomeItemBinding.inflate(LayoutInflater.from(parent.context)))
                }
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when(holder){
                is HeaderViewHolder ->{
                    holder.bind()
                }
                is TitleViewHolder ->{
                    holder.bind(viewModel.sectionList[position-1])
                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            return when(position){
                0 -> TYPE_HEADER
                else -> TYPE_TITLE
            }
        }

        override fun getItemCount(): Int {
            return viewModel.sectionList.size + 1
        }

        inner class TitleViewHolder(private val binding: FragmentHomeItemBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(data:String){
                binding.tvSection.text = data
                binding.rvTitle.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                viewModel.getSactionData(data){
                    binding.rvTitle.adapter = SectionAdapter(it)
                }
            }
        }

        inner class HeaderViewHolder(private val binding: FragmentHomeHeaderBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(){
                viewModel.getSactionData(viewModel.sectionList[0]){
                    val url = "https://image.tmdb.org/t/p/w500/${it.random().poster_path}"
                    Glide.with(requireContext())
                        .load(url)
                        .centerCrop()
                        .placeholder(R.drawable.heroimage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.imgBanner)
                }

            }
        }
    }

    inner class SectionAdapter(var dataList :ArrayList<Title>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ViewHolder(FragmentHomeItemRowBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is ViewHolder) {
                holder.bind(dataList[position])
            }
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        inner class ViewHolder(private val binding: FragmentHomeItemRowBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(data:Title){
                val url = "https://image.tmdb.org/t/p/w500/${data.poster_path}"
                Log.d("SectionAdapter","Title:${data.original_name}, url : $url")
                Glide.with(requireContext())
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.heroimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imgTitle)
            }
        }
    }

}