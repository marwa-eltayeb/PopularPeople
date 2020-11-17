package com.marwaeltayeb.popularpeople.model

import com.google.gson.annotations.SerializedName

class ImageApiResponse (

    @SerializedName("id")
    val id: Int,

    @SerializedName("profiles")
    val imagesList: List<Image>
)