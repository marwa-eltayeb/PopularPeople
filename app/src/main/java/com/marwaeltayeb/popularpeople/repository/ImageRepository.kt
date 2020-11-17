package com.marwaeltayeb.popularpeople.repository

import android.app.Application
import android.util.Log

import androidx.lifecycle.MutableLiveData
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.model.ImageApiResponse
import com.marwaeltayeb.popularpeople.network.RetrofitClient
import com.marwaeltayeb.popularpeople.utils.Const
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImageRepository {

    private var imagesList: List<Image> = ArrayList()
    private val mutableLiveData: MutableLiveData<List<Image>> = MutableLiveData<List<Image>>()

    fun getMutableLiveData(actorId: String): MutableLiveData<List<Image>> {
        RetrofitClient.getActorService().getActorImages(actorId, Const.API_KEY)
            .enqueue(object : Callback<ImageApiResponse> {
                override fun onFailure(call: Call<ImageApiResponse>, t: Throwable) {
                    Log.d("onFailure", "Failed")
                }

                override fun onResponse(call: Call<ImageApiResponse>, response: Response<ImageApiResponse>) {
                    Log.d("onResponse", "Succeeded")

                    if (response.body() != null) {
                        imagesList = response.body()!!.imagesList
                        mutableLiveData.setValue(imagesList);
                    }
                }
            })
        return mutableLiveData
    }
}