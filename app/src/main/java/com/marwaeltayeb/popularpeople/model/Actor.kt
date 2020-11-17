package com.marwaeltayeb.popularpeople.model

import android.R
import com.google.gson.annotations.SerializedName

class Acting (

    @SerializedName("popularity")
    val popularity: String,

    @SerializedName("known_for_department")
    val department: String,

    @SerializedName("gender")
    val gender: Int,

    @SerializedName("id")
    val actorId: Int,

    @SerializedName("profile_path")
    val actorImage: String,

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("known_for")
    val actorsList: List<Work>,

    @SerializedName("name")
    val actorName: String,
)

class Work (
    @SerializedName("title")
    val movieTitle: String,
)


