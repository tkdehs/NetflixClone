package com.pnxand.netflixclone.view.core.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.databinding.FragmentDownloadBinding
import com.pnxand.netflixclone.databinding.FragmentHomeBinding
import com.pnxand.netflixclone.databinding.FragmentMainBinding
import com.pnxand.netflixclone.databinding.FragmentSearchBinding
import com.pnxand.netflixclone.view.base.BaseFragment

class DownloadFragment :BaseFragment() {

    private val viewModel: DownloadViewModel by viewModels()

    lateinit var binding:FragmentDownloadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDownloadBinding.inflate(LayoutInflater.from(requireContext()))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

}