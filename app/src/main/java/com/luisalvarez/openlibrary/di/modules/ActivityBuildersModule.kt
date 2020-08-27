package com.luisalvarez.openlibrary.di.modules

import com.luisalvarez.openlibrary.MainActivity
import com.luisalvarez.openlibrary.di.annotations.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuildersModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class, ActivityModule::class])
    abstract fun createSaleDetailsActivityInjector(): MainActivity
}