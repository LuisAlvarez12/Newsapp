package com.luisalvarez.openlibrary.di.modules

import androidx.appcompat.app.AppCompatActivity
import com.luisalvarez.openlibrary.MainActivity
import com.luisalvarez.openlibrary.di.annotations.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {
    @Binds
    @PerActivity
    abstract fun provideBaseActivity(activity: MainActivity): AppCompatActivity
}