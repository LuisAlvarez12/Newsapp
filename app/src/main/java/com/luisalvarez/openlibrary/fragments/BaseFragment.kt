package com.luisalvarez.openlibrary.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.luisalvarez.openlibrary.MainActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val layout: Int
    open val customTransition: Int? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        customTransition?.let {
            sharedElementEnterTransition =
                TransitionInflater.from(requireContext())
                    .inflateTransition(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    open fun <T : ViewModel?> getViewModel(clazz: Class<T>): T {
        return ViewModelProviders.of(this).get(clazz)
    }

    fun showBottomNav(show: Boolean) {
        (activity as? MainActivity)?.showBottomNav(show)
    }

}

inline fun <reified T : ViewModel> Fragment.withViewModel(
    source: FragmentActivity? = null,
    viewModelFactory: ViewModelProvider.Factory
): T {
    return source?.let {
        ViewModelProviders.of(it, viewModelFactory).get(T::class.java)
    } ?: ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}
