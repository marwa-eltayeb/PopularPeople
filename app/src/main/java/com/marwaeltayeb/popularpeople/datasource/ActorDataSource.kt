package com.marwaeltayeb.popularpeople.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.marwaeltayeb.popularpeople.utils.Const
import com.marwaeltayeb.popularpeople.model.Actor
import com.marwaeltayeb.popularpeople.network.ActorService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ActorDataSource(private val scope: CoroutineScope, private val actorService : ActorService) : PageKeyedDataSource<Int, Actor>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Actor>) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = actorService.getActorsList(Const.API_KEY, Const.LANGUAGE, Const.FIRST_PAGE)

                Log.d("onResponse", "Succeeded")
                if (response.isSuccessful) {
                    // Fetch database and pass the result null for the previous page
                    callback.onResult(response.body()!!.actorsList, null, Const.FIRST_PAGE + 1)
                }
            } catch (e: Exception) {
                // Show API error. This is the error raised by the client.
                Log.d("onFailure", "Failed")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Actor>) {
        scope.launch(Dispatchers.IO) {
            try {
                Log.d("onResponse", "Succeeded")

                val response = actorService.getActorsList(Const.API_KEY, Const.LANGUAGE, params.key)

                val adjacentKey = if (params.key > 1) params.key - 1 else null

                if (response.body() != null) {
                    // Passing the loaded database and the previous page key
                    callback.onResult(response.body()!!.actorsList, adjacentKey)
                }
            } catch (e: Exception) {
                // Show API error. This is the error raised by the client.
                Log.d("onFailure", "Failed")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Actor>) {

        scope.launch(Dispatchers.IO) {
            try {
                Log.d("onResponse", "Succeeded")

                val response = actorService.getActorsList(Const.API_KEY, Const.LANGUAGE, params.key)

                if (response.body() != null) {
                    // If the response has next page, increment the next page number
                    val key = if (response.body()!!.actorsList.size == Const.PAGE_SIZE) params.key + 1 else null

                    // Passing the loaded database and next page value
                    callback.onResult(response.body()!!.actorsList, key)
                }
            } catch (e: Exception) {
                // Show API error. This is the error raised by the client.
                Log.d("onFailure", "Failed")
            }
        }
    }
}