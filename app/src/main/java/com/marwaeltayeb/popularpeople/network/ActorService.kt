package com.marwaeltayeb.popularpeople.network

import com.marwaeltayeb.popularpeople.model.ActorApiResponse
import com.marwaeltayeb.popularpeople.model.ImageApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ActorService {

    @GET("popular")
    fun getActorsList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ActorApiResponse>

    @GET("{actorId}/images")
    fun getActorImages(
        @Path("actorId") actorId: String,
        @Query("api_key") apiKey: String
    ): Call<ImageApiResponse>
}