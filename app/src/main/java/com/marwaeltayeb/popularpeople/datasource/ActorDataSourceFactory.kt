package com.marwaeltayeb.popularpeople.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.marwaeltayeb.popularpeople.model.Actor
import kotlinx.coroutines.CoroutineScope

class ActorDataSourceFactory(private val scope: CoroutineScope): DataSource.Factory<Int, Actor>() {
    
    // Creating the mutable live database
    private val actorLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Actor>>()

    override fun create(): DataSource<Int, Actor> {
        // Getting our Data source object
        val actorDataSource = ActorDataSource(scope)

        // Posting the Data source to get the values
        actorLiveDataSource.postValue(actorDataSource)

        // Returning the Data source
        return actorDataSource
    }

    // Getter for Actor live DataSource
    fun getActorsLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Actor>> {
        return actorLiveDataSource
    }
}


