package com.luisalvarez.openlibrary.di.modules

import androidx.lifecycle.ViewModel
import com.luisalvarez.openlibrary.di.annotations.ViewModelKey
import com.luisalvarez.openlibrary.viewmodels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

}




