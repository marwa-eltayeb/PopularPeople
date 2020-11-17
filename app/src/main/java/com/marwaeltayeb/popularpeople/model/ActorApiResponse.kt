package com.marwaeltayeb.popularpeople.model

import com.google.gson.annotations.SerializedName

data class ActorApiResponse (

    @SerializedName("page")
    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val actorsList: List<Acting>
)