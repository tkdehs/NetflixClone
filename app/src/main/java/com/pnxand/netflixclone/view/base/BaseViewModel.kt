package com.pnxand.netflixclone.view.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

open class BaseViewModel : ViewModel() {
    fun networkThread(callback:suspend (CoroutineScope)->Unit){
        viewModelScope.launch {
            try {
                callback(this)
            } catch ( e: Exception) {
                e.printStackTrace()
                Log.d("networkThread","네트워크 에러")
            } finally {

            }
        }
    }
}