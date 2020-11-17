package com.marwaeltayeb.popularpeople.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.marwaeltayeb.popularpeople.utils.Const
import com.marwaeltayeb.popularpeople.model.Acting
import com.marwaeltayeb.popularpeople.datasource.ActorDataSourceFactory


class ActorViewModel : ViewModel() {

    // Create liveData for PagedList and PagedKeyedDataSource
    var actingPagedList: LiveData<PagedList<Acting>>
    private var liveDataSource: LiveData<PageKeyedDataSource<Int, Acting>>

    init {
        
        // Get our database source factory
        val actingDataSourceFactory = ActorDataSourceFactory()


        // Get the live database source from database source factory
        liveDataSource = actingDataSourceFactory.getActingLiveDataSource()


        // Get PagedList configuration
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Const.PAGE_SIZE).build()


        // Build the paged list
        actingPagedList = LivePagedListBuilder(actingDataSourceFactory, pagedListConfig).build()
    }
}