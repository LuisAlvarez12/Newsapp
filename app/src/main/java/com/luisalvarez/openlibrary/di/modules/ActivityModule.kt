package com.luisalvarez.openlibrary.di.modules

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityModule {

    @Binds
    abstract fun provideAppCompatActivity(activity: AppCompatActivity): AppCompatActivity

    @Binds
    abstract fun provideActivity(activity: AppCompatActivity): Activity
}