package com.marwaeltayeb.popularpeople.di.main

import androidx.lifecycle.ViewModel
import com.marwaeltayeb.popularpeople.di.ViewModelKey
import com.marwaeltayeb.popularpeople.viewmodel.ActorViewModel
import com.marwaeltayeb.popularpeople.viewmodel.DetailsViewModel
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
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindImageViewModel(viewModel: DetailsViewModel): ViewModel
}