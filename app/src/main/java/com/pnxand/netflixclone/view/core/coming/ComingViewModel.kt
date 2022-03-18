package com.pnxand.netflixclone.view.core.coming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.manager.NetworkService
import com.pnxand.netflixclone.model.Title
import com.pnxand.netflixclone.view.base.BaseViewModel

class ComingViewModel : BaseViewModel() {
    private val _itemList = MutableLiveData<MutableList<Title>>()
    val itemList:LiveData<MutableList<Title>> get() = _itemList

    fun getList(){
        networkThread {
            val result = NetworkService().getApiService().getUpcomingMovies()
            _itemList.value = result.results
        }
    }
}