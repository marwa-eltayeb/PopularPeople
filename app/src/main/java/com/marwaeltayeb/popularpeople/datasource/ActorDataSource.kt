package com.marwaeltayeb.popularpeople.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.marwaeltayeb.popularpeople.utils.Const
import com.marwaeltayeb.popularpeople.model.Actor
import com.marwaeltayeb.popularpeople.model.ActorApiResponse
import com.marwaeltayeb.popularpeople.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ActorDataSource : PageKeyedDataSource<Int, Actor>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Actor>) {
        RetrofitClient.getActorService().getActorsList(Const.API_KEY, Const.LANGUAGE, Const.FIRST_PAGE)
            .enqueue(object : Callback<ActorApiResponse> {
                override fun onFailure(call: Call<ActorApiResponse>, t: Throwable) {
                    Log.d("onFailure", "Failed")
                }

                override fun onResponse(call: Call<ActorApiResponse>, response: Response<ActorApiResponse>) {
                    Log.d("onResponse", "Succeeded")

                    if (response.isSuccessful) {
                        // Fetch database and pass the result null for the previous page
                        callback.onResult(response.body()!!.actorsList, null, Const.FIRST_PAGE + 1)
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Actor>) {

        RetrofitClient.getActorService().getActorsList(Const.API_KEY, Const.LANGUAGE, Const.FIRST_PAGE)
            .enqueue(object : Callback<ActorApiResponse> {
                override fun onFailure(call: Call<ActorApiResponse>, t: Throwable) {
                    Log.d("onFailure", "Failed")
                }

                override fun onResponse(call: Call<ActorApiResponse>, response: Response<ActorApiResponse>) {
                    Log.v("onResponse", "Succeeded")

                    val adjacentKey = if (params.key > 1) params.key - 1 else null

                    if (response.body() != null) {
                        // Passing the loaded database and the previous page key
                        callback.onResult(response.body()!!.actorsList, adjacentKey)
                    }
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Actor>) {

        RetrofitClient.getActorService().getActorsList(Const.API_KEY, Const.LANGUAGE, Const.FIRST_PAGE)
            .enqueue(object : Callback<ActorApiResponse> {
                override fun onFailure(call: Call<ActorApiResponse>, t: Throwable) {
                    Log.d("onFailure", "Failed")
                }

                override fun onResponse(call: Call<ActorApiResponse>, response: Response<ActorApiResponse>) {
                    Log.v("onResponse", "Succeeded")

                    if (response.body() != null) {
                        // If the response has next page, increment the next page number
                        val key = if (response.body()!!.actorsList.size == Const.PAGE_SIZE) params.key + 1 else null

                        // Passing the loaded database and next page value
                        callback.onResult(response.body()!!.actorsList, key)
                    }
                }
            })
    }
}