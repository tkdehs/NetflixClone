package com.pnxand.netflixclone.view.core.coming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.databinding.FragmentComingBinding
import com.pnxand.netflixclone.databinding.FragmentHomeBinding
import com.pnxand.netflixclone.databinding.FragmentMainBinding
import com.pnxand.netflixclone.view.base.BaseFragment
import com.pnxand.netflixclone.view.core.adapter.TvAdapter

class ComingFragment :BaseFragment() {

    private val viewModel: ComingViewModel by viewModels()

    lateinit var binding:FragmentComingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentComingBinding.inflate(LayoutInflater.from(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.rvUpcoming.adapter = TvAdapter(requireActivity())
        binding.rvUpcoming.layoutManager = GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false)
        observe()
        viewModel.getList()
        return binding.root
    }

    private fun observe(){
        viewModel.itemList.observe(viewLifecycleOwner){
            val adapter =  binding.rvUpcoming.adapter as? TvAdapter
            adapter?.submitList(it)
        }
    }
}