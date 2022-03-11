package com.pnxand.netflixclone.view.core.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.databinding.FragmentMainBinding
import com.pnxand.netflixclone.view.base.BaseFragment
import com.pnxand.netflixclone.view.core.coming.ComingFragment
import com.pnxand.netflixclone.view.core.download.DownloadFragment
import com.pnxand.netflixclone.view.core.home.HomeFragment
import com.pnxand.netflixclone.view.core.search.SearchFragment

class MainFragment :BaseFragment() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding:FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(LayoutInflater.from(requireContext()))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.bottomNaviView.setOnItemSelectedListener {
            selectedContent(it.itemId)
            true
        }
        selectedContent(R.id.home)

        return binding.root
    }

    private fun selectedContent(itemId: Int) {
        viewModel.setSelectedMenuId(itemId)
        when (itemId) {
            R.id.home -> {
                setMainContent(HomeFragment::class.java)
            }
            R.id.comimg -> {
                setMainContent(ComingFragment::class.java)
            }
            R.id.search -> {
                setMainContent(SearchFragment::class.java)
            }
            R.id.downlaod -> {
                setMainContent(DownloadFragment::class.java)
            }
        }
    }
    private fun setMainContent(fragmentClass: Class<out Fragment?>) {
        childFragmentManager.fragments.forEach {
            childFragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
        }
        val fragment = childFragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (fragment == null) {
            childFragmentManager.beginTransaction()
                .add(binding.mainConentLayout.id, fragmentClass, null, fragmentClass.simpleName)
                .commitAllowingStateLoss()
        } else {
            childFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss()
        }
    }
}