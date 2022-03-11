package com.pnxand.netflixclone.view.core.coming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.databinding.FragmentComingBinding
import com.pnxand.netflixclone.databinding.FragmentHomeBinding
import com.pnxand.netflixclone.databinding.FragmentMainBinding
import com.pnxand.netflixclone.view.base.BaseFragment

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

        return binding.root
    }

}