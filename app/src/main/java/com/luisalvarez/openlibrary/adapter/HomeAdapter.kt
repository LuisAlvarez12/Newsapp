package com.luisalvarez.openlibrary.adapter

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.luisalvarez.openlibrary.R
import com.luisalvarez.openlibrary.extensions.updateHorizontalListOrClear
import com.luisalvarez.openlibrary.item.ArticleScaffoldingItem
import com.luisalvarez.openlibrary.item.SearchHeaderItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class HomeAdapter(
    resources: Resources,
    private val recycledViewPool: RecyclerView.RecycledViewPool
) :
    GroupAdapter<GroupieViewHolder>() {
    private val welcomeSection = Section()
    private val categorySectionCovid = Section()
    private val categorySectionPolice = Section()

    init {
        categorySectionPolice.setHeader(
            SearchHeaderItem(
                resources.getString(R.string.section_header_police)
            )
        )
        categorySectionCovid.setHeader(
            SearchHeaderItem(
                resources.getString(R.string.section_header_covid)
            )
        )

        welcomeSection.setHideWhenEmpty(false)

        add(welcomeSection)
        add(categorySectionPolice)
        add(categorySectionCovid)
    }

    fun updatePopularSection(items: List<Item<GroupieViewHolder>>?) {
        categorySectionCovid.updateHorizontalListOrClear(items, recycledViewPool)
    }

    fun updateRecommendedSection(items: List<Item<GroupieViewHolder>>?) {
        categorySectionPolice.updateHorizontalListOrClear(items, recycledViewPool)
    }

    fun showScaffolding() {
        val scaffoldingItems = MutableList(3) { index -> ArticleScaffoldingItem() }

        updateRecommendedSection(scaffoldingItems)
        updatePopularSection(scaffoldingItems)
    }
}