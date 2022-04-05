package com.example.spotifymusicrecommendation.api

import com.example.spotifymusicrecommendation.data.Music
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://music-recom.herokuapp.com/"

interface APIService {
    @GET("music")
    suspend fun getMusic(): List<Music>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}