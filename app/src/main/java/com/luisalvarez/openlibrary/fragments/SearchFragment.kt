package com.luisalvarez.openlibrary.fragments

import com.luisalvarez.openlibrary.R

class SearchFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.fragment_search

    override val customTransition: Int
        get() = R.transition.shared_element_default

}