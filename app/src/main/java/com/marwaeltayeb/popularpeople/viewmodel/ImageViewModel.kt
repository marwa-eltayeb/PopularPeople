package com.marwaeltayeb.popularpeople.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.repository.ImageRepository


class ImageViewModel(application: Application) : AndroidViewModel(application) {

   private var imageRepository: ImageRepository = ImageRepository()

    fun getAllImages(actorId: String): LiveData<List<Image>> {
        return imageRepository.getMutableLiveData(actorId,viewModelScope)
    }
}