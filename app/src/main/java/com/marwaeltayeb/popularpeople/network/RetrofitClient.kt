package com.marwaeltayeb.popularpeople.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitClient {

    private val retrofit: Retrofit
    private lateinit var retrofitClient: RetrofitClient
    private val BASE_URL = "https://api.themoviedb.org/3/person/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getActorService(): ActorService {
        return retrofit.create(ActorService::class.java)
    }
}