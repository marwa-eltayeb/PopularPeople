package com.marwaeltayeb.popularpeople.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.network.ActorService
import com.marwaeltayeb.popularpeople.repository.ImageRepository
import javax.inject.Inject


class ImageViewModel @Inject constructor(application: Application, private val actorService : ActorService) : AndroidViewModel(application) {

   private var imageRepository: ImageRepository = ImageRepository()

    fun getAllImages(actorId: String): LiveData<List<Image>> {
        return imageRepository.getMutableLiveData(actorId,viewModelScope, actorService)
    }
}