package com.marwaeltayeb.popularpeople.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.marwaeltayeb.popularpeople.model.Acting

class ActorDataSourceFactory: DataSource.Factory<Int, Acting>() {
    
    // Creating the mutable live database
    private val actingLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Acting>>()

    override fun create(): DataSource<Int, Acting> {
        // Getting our Data source object
        val actingDataSource = ActorDataSource()

        // Posting the Data source to get the values
        actingLiveDataSource.postValue(actingDataSource)

        // Returning the Data source
        return actingDataSource
    }

    // Getter for Acting live DataSource
    fun getActingLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Acting>> {
        return actingLiveDataSource
    }
}


