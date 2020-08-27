package com.luisalvarez.openlibrary.item

import com.luisalvarez.openlibrary.R
import com.luisalvarez.openlibrary.extensions.ScaffoldingItem
import com.luisalvarez.openlibrary.network.model.searchResponse.NewsResult
import com.luisalvarez.openlibrary.services.load
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_article.view.*
import java.text.SimpleDateFormat
import java.util.*


class ArticleItem(
    private val newsItem: NewsResult,
    private val onCardClicked: (NewsResult) -> Unit
) :
    Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.item_article

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            image.load(newsItem.fields.thumbnail)
            title.text = newsItem.webTitle
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val convertedCurrentDate: Date = sdf.parse(newsItem.webPublicationDate)
            date.text = sdf.format(convertedCurrentDate)
            setOnClickListener {
                onCardClicked.invoke(newsItem)
            }
        }

    }
}

class ArticleScaffoldingItem : ScaffoldingItem(R.layout.item_article_scaffolding)