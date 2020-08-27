package com.luisalvarez.openlibrary.di.modules

import androidx.lifecycle.ViewModelProvider
import com.luisalvarez.openlibrary.viewmodels.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class DaggerViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}