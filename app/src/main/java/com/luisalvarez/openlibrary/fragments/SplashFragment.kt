package com.luisalvarez.openlibrary.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.luisalvarez.openlibrary.R
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // launch shared element animation
        GlobalScope.launch {
            delay(DELAY)

            val extras = FragmentNavigatorExtras(
                icon to icon.transitionName,
                header_text to header_text.transitionName
            )

            findNavController().navigate(R.id.confirmationAction, null, null, extras)
        }
    }

    companion object {

        // delay before launching into home
        const val DELAY: Long = 800
    }
}