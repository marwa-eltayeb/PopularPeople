package com.marwaeltayeb.popularpeople.di.main

import androidx.lifecycle.ViewModel
import com.marwaeltayeb.popularpeople.di.ViewModelKey
import com.marwaeltayeb.popularpeople.viewmodel.ActorViewModel
import com.marwaeltayeb.popularpeople.viewmodel.ImageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ActorViewModel::class)
    abstract fun bindActorViewModel(viewModel: ActorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel::class)
    abstract fun bindImageViewModel(viewModel: ImageViewModel): ViewModel
}