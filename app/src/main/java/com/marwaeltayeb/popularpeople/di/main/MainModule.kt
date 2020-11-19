package com.marwaeltayeb.popularpeople.di.main

import com.marwaeltayeb.popularpeople.adapter.ActorAdapter
import com.marwaeltayeb.popularpeople.adapter.ImageAdapter
import com.marwaeltayeb.popularpeople.network.ActorService
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class MainModule {

    @Provides
    fun provideActorService(retrofit: Retrofit): ActorService{
        return retrofit.create(ActorService::class.java)
    }

    @Provides
    fun provideActorAdapter() : ActorAdapter {
        return ActorAdapter()
    }

    @Provides
    fun provideImageAdapter() : ImageAdapter {
        return ImageAdapter()
    }
}