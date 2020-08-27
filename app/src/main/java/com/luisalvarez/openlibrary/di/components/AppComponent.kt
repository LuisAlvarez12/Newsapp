package com.luisalvarez.openlibrary.di.components

import com.luisalvarez.openlibrary.di.NewsApp
import com.luisalvarez.openlibrary.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentBuildersModule::class,
        ViewModelModule::class,
        ActivityBuildersModule::class,
        NetworkModule::class,
        DaggerViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<NewsApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: NewsApp): Builder

        fun build(): AppComponent
    }

    override fun inject(app: NewsApp)
}