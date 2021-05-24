package com.marwaeltayeb.popularpeople.repository

import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.network.ActorService
import com.marwaeltayeb.popularpeople.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsRepository {

     suspend fun getMutableLiveData(actorId: String, actorService : ActorService): List<Image> {
        var imageList: List<Image> = ArrayList()
            withContext(Dispatchers.IO) {
                val response = actorService.getActorImages(actorId, Const.API_KEY)

                response.body()?.let {
                    imageList = it.imagesList
                }
        }
        return imageList
    }
}