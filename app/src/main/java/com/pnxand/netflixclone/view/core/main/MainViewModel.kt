package com.pnxand.netflixclone.view.core.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pnxand.netflixclone.R

class MainViewModel : ViewModel() {
    private val _selectedMainTabMenu = MutableLiveData(R.id.home)
    val selectedMainTabMenu: LiveData<Int> = _selectedMainTabMenu

    fun setSelectedMenuId(id:Int) {
        _selectedMainTabMenu.value = id
    }
}