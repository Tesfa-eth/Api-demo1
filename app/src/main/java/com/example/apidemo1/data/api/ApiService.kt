package com.example.apidemo1.data.api

import retrofit2.Retrofit
import retrofit2.http.GET

import com.example.apidemo1.data.api.model.Character
import  retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://hp-api-bfrenchrit.koyeb.app/hp-api/api/"

interface  APIService{
    @GET("characters")
    suspend fun getCharacters(): List<Character>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        } // getInstance

    } //companion object
} //interface
