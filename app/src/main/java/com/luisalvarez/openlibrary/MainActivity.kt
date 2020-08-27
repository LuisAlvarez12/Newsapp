package com.luisalvarez.openlibrary

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            bottom_navigation,
            navHostFragment!!.navController
        )
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    fun showBottomNav(show: Boolean) {
        if (bottom_navigation.visibility != View.VISIBLE) {
            val searchContainerAnimation =
                AnimationUtils.loadAnimation(this, R.anim.home_pop_up)
            bottom_navigation.startAnimation(searchContainerAnimation)
            bottom_navigation.visibility = View.VISIBLE
        }

    }
}
