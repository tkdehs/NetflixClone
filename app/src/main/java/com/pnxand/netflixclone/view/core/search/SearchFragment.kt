package com.pnxand.netflixclone.view.core.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.databinding.FragmentHomeBinding
import com.pnxand.netflixclone.databinding.FragmentMainBinding
import com.pnxand.netflixclone.databinding.FragmentSearchBinding
import com.pnxand.netflixclone.view.base.BaseFragment

class SearchFragment :BaseFragment() {

    private val viewModel: SearchViewModel by viewModels()

    lateinit var binding:FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchBinding.inflate(LayoutInflater.from(requireContext()))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

}