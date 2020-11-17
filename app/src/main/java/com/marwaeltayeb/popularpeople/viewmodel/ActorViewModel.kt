package com.marwaeltayeb.popularpeople.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.marwaeltayeb.popularpeople.utils.Const
import com.marwaeltayeb.popularpeople.model.Actor
import com.marwaeltayeb.popularpeople.datasource.ActorDataSourceFactory


class ActorViewModel : ViewModel() {

    // Create liveData for PagedList and PagedKeyedDataSource
    var actorPagedList: LiveData<PagedList<Actor>>
    private var liveDataSource: LiveData<PageKeyedDataSource<Int, Actor>>

    init {
        
        // Get our database source factory
        val actorDataSourceFactory = ActorDataSourceFactory()


        // Get the live database source from database source factory
        liveDataSource = actorDataSourceFactory.getActorsLiveDataSource()


        // Get PagedList configuration
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Const.PAGE_SIZE).build()


        // Build the paged list
        actorPagedList = LivePagedListBuilder(actorDataSourceFactory, pagedListConfig).build()
    }
}