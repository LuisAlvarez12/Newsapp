package com.luisalvarez.openlibrary.item

import com.luisalvarez.openlibrary.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_home_headers.view.*

class SearchHeaderItem(private val headerText: String) : Item<GroupieViewHolder>() {

    override fun getLayout() =
        R.layout.item_home_headers

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.text.text = headerText
    }
}