package com.marwaeltayeb.popularpeople.di

import com.marwaeltayeb.popularpeople.di.main.MainModule
import com.marwaeltayeb.popularpeople.di.main.MainViewModelsModule
import com.marwaeltayeb.popularpeople.view.DetailsActivity
import com.marwaeltayeb.popularpeople.view.ImageActivity
import com.marwaeltayeb.popularpeople.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {


    @ContributesAndroidInjector(modules = [MainViewModelsModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MainViewModelsModule::class, MainModule::class])
    abstract fun contributeDetailsActivity(): DetailsActivity

    @ContributesAndroidInjector(modules = [MainViewModelsModule::class, MainModule::class])
    abstract fun contributeImageActivity(): ImageActivity
}