package com.marwaeltayeb.popularpeople.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image (
    @SerializedName("file_path")
    val image: String,
): Serializable