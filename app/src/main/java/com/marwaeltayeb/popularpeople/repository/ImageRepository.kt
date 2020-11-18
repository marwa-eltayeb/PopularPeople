package com.marwaeltayeb.popularpeople.repository

import android.util.Log

import androidx.lifecycle.MutableLiveData
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.network.RetrofitClient
import com.marwaeltayeb.popularpeople.utils.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ImageRepository {

    private var imagesList: List<Image> = ArrayList()
    private val mutableLiveData: MutableLiveData<List<Image>> = MutableLiveData<List<Image>>()

    fun getMutableLiveData(actorId: String, scope: CoroutineScope): MutableLiveData<List<Image>> {
        scope.launch(Dispatchers.IO) {
            try {

                val response =
                    RetrofitClient.getActorService().getActorImages(actorId, Const.API_KEY)
                Log.d("onResponse", "Succeeded")

                if (response.body() != null) {
                    imagesList = response.body()!!.imagesList
                    mutableLiveData.setValue(imagesList);
                }


            } catch (e: Exception) {
                // Show API error. This is the error raised by the client.
                Log.d("onFailure", "Failed")
            }
        }
        return mutableLiveData
    }
}