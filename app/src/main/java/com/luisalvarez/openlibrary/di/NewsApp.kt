package com.luisalvarez.openlibrary.di

import com.luisalvarez.openlibrary.di.components.AppComponent
import com.luisalvarez.openlibrary.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class NewsApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return applicationBuilder().build()
    }

    fun applicationBuilder(): AppComponent.Builder {
        return DaggerAppComponent.builder()
            .application(this)
    }

}