package com.example.spotifymusicrecommendation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifymusicrecommendation.api.APIService
import com.example.spotifymusicrecommendation.data.Music
import kotlinx.coroutines.launch
import java.lang.Exception

class MusicViewModel : ViewModel() {
    private val _musicList = mutableListOf<Music>()
    var errorMessage: String by mutableStateOf("")

    val musicList: List<Music> get() = _musicList

    fun getMusicList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _musicList.clear()
                _musicList.addAll(apiService.getMusic())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}