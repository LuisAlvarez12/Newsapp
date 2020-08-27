package com.luisalvarez.openlibrary.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.luisalvarez.openlibrary.R
import com.luisalvarez.openlibrary.adapter.HomeAdapter
import com.luisalvarez.openlibrary.item.ArticleItem
import com.luisalvarez.openlibrary.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.dialog_article_details.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.fragment_home

    override val customTransition: Int
        get() = R.transition.shared_element_default

    private lateinit var viewModel: HomeViewModel
    private lateinit var groupAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = withViewModel(viewModelFactory = viewModelFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter handling
        groupAdapter = HomeAdapter(resources, recycler_view.recycledViewPool)
        recycler_view.adapter = groupAdapter
        groupAdapter.showScaffolding()

        setupViewModel()

        showBottomNav(true)
    }

    private fun setupViewModel() {
        viewModel.category1Items.observe(viewLifecycleOwner, Observer {
            groupAdapter.updatePopularSection(it.map { ArticleItem(it, viewModel::onCardClicked) })
        })

        viewModel.category2Items.observe(viewLifecycleOwner, Observer {
            groupAdapter.updateRecommendedSection(it.map {
                ArticleItem(
                    it,
                    viewModel::onCardClicked
                )
            })
        })

        viewModel.showArticleDialog.observe(viewLifecycleOwner, Observer {
            val dialog =
                Dialog(requireContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
            dialog.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            dialog.setContentView(R.layout.dialog_article_details)
            dialog.webview.loadUrl(it.webUrl)
            dialog.close.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        })

        viewModel.init()
    }
}
