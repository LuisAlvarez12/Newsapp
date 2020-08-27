package com.luisalvarez.openlibrary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.luisalvarez.openlibrary.R
import kotlinx.android.synthetic.main.dialog_article_details.*

class WebviewBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.dialog_article_details, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            webview.loadUrl(WebviewBottomSheetFragmentArgs.fromBundle(it).webUrl)
            close.setOnClickListener { dismiss() }
        }
    }
}