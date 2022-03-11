package com.pnxand.netflixclone.view.core.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pnxand.netflixclone.R
import com.pnxand.netflixclone.manager.NetworkService
import com.pnxand.netflixclone.model.Title
import com.pnxand.netflixclone.view.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    val sectionList = arrayListOf("Trending Movies" ,"Trending Tv" ,"Popular", "Upcoming Movies", "Top rated")

    fun getSactionData(saction:String,callback:(ArrayList<Title>)->Unit) {
       networkThread {
           when(saction){
               sectionList[0]->{
                   val result = NetworkService().getApiService().getTrendingMovies()
                   callback(result.results)
               }
           }
       }
    }
}