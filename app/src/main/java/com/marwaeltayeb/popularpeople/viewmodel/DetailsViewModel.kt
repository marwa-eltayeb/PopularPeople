package com.marwaeltayeb.popularpeople.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.marwaeltayeb.popularpeople.model.Image
import com.marwaeltayeb.popularpeople.network.ActorService
import com.marwaeltayeb.popularpeople.repository.DetailsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DetailsViewModel"

class DetailsViewModel @Inject constructor(application: Application, private val actorService : ActorService) : AndroidViewModel(application) {

   private var detailsRepository: DetailsRepository = DetailsRepository()
   private val mutableLiveData: MutableLiveData<List<Image>> = MutableLiveData<List<Image>>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, exception.message.toString())
    }

    fun requestImageList(actorId: String){
        viewModelScope.launch(coroutineExceptionHandler){
            val imagesList:List<Image> = detailsRepository.getMutableLiveData(actorId, actorService)
            mutableLiveData.setValue(imagesList)
        }
    }

    fun getAllImages(): LiveData<List<Image>> {
        return mutableLiveData
    }
}