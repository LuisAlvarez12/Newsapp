package com.luisalvarez.openlibrary.di.modules

import com.luisalvarez.openlibrary.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun createSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun createHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun createDetailsFragment(): DetailsFragment

    @ContributesAndroidInjector
    abstract fun createSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun createFavoritesFragment(): FavoritesFragment
}