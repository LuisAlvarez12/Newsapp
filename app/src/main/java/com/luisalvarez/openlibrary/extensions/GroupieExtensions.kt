package com.luisalvarez.openlibrary.extensions

import androidx.recyclerview.widget.RecyclerView
import com.luisalvarez.openlibrary.item.HorizontalRecyclerSectionItem
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.Section

fun Section.updateOrClear(items: List<Item<GroupieViewHolder>>?) {
    items?.let {
        if (it.isNotEmpty()) {
            this.update(it)
        } else {
            this.clear()
        }
    }
}

fun Section.updateHorizontalListOrClear(
    items: List<Item<GroupieViewHolder>>?,
    recycledViewPool: RecyclerView.RecycledViewPool
) {
    items?.let {
        if (it.isNotEmpty()) {
            this.update(
                listOf(
                    HorizontalRecyclerSectionItem(
                        items,
                        recycledViewPool,
                        emptyList(),
                        6
                    )
                )
            )
        } else {
            this.clear()
        }
    }
}

abstract class ScaffoldingItem(private val scaffoldingLayout: Int) : Item<GroupieViewHolder>() {
    override fun getLayout(): Int = scaffoldingLayout
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }
}
