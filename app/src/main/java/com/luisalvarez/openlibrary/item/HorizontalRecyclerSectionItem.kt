package com.luisalvarez.openlibrary.item

import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisalvarez.openlibrary.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_horizontal_scrolling.view.*
import com.xwray.groupie.Item as GroupieItem

class HorizontalRecyclerSectionItem(
    initialData: List<com.xwray.groupie.Item<com.xwray.groupie.GroupieViewHolder>>? = null,
    private var viewPool: RecyclerView.RecycledViewPool,
    private val itemDecorations: List<RecyclerView.ItemDecoration> = emptyList(),
    private val viewCacheSize: Int? = null
) : Item() {

    private val innerAdapter: GroupAdapter<GroupieViewHolder> = GroupAdapter<GroupieViewHolder>()
    private var layoutManager: RecyclerView.LayoutManager? = null

    private var state: Parcelable? = null

    init {
        if (initialData != null) {
            innerAdapter.update(initialData)
            notifyChanged()
        }
    }

    fun updateData(data: List<GroupieItem<GroupieViewHolder>>) {
        innerAdapter.update(data)
        notifyChanged()
    }

    override fun createViewHolder(itemView: View): GroupieViewHolder {
        return super.createViewHolder(itemView).apply {
            itemView.recycler.apply {
                if (viewCacheSize != null) {
                    setItemViewCacheSize(viewCacheSize)
                }

                setRecycledViewPool(viewPool)
                this@HorizontalRecyclerSectionItem.layoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false).apply {
                        // Allows for recycler view to change its height based on varied inner content
                        isMeasurementCacheEnabled = false
                        initialPrefetchItemCount = 6
                    }

                layoutManager = this@HorizontalRecyclerSectionItem.layoutManager
                adapter = innerAdapter
            }
        }
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.recycler.run {
            if (this@HorizontalRecyclerSectionItem.layoutManager != layoutManager) {
                this@HorizontalRecyclerSectionItem.layoutManager = layoutManager
            }
            if (viewCacheSize != null) {
                setItemViewCacheSize(viewCacheSize)
            }

            if (state != null) {
                layoutManager?.onRestoreInstanceState(state)
            }

            if (adapter != innerAdapter) {
                // View was recycled with incorrect adapter
                adapter = innerAdapter
            }
            // Remove all item decorations
            val currentDecorations = (0 until this.itemDecorationCount).map {
                getItemDecorationAt(it)
            }
            currentDecorations.forEach {
                if (!itemDecorations.contains(it)) {
                    removeItemDecoration(it)
                }
            }

            itemDecorations.subtract(currentDecorations).forEach {
                addItemDecoration(it)
            }

            clearOnScrollListeners()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    viewHolder.itemView.recycler.layoutManager?.requestLayout()
                }
            })
        }
    }

    fun getInnerItem(position: Int): GroupieItem<*>? {
        return innerAdapter.getItem(position) as? GroupieItem
    }

    fun getInnerItemCount(): Int? {
        return innerAdapter.itemCount
    }

    override fun unbind(holder: GroupieViewHolder) {
        super.unbind(holder)
        holder.itemView.recycler?.run {
            state = layoutManager?.onSaveInstanceState()

            clearOnScrollListeners()
            // Remove all item decorations
            (0 until this.itemDecorationCount).forEach {
                removeItemDecorationAt(it)
            }
            adapter = null
        }
    }

    fun saveState(): Parcelable? {
        state = layoutManager?.onSaveInstanceState()
        return state
    }

    fun restoreState(state: Parcelable) {
        this.state = state
        layoutManager?.onRestoreInstanceState(state)
        notifyChanged()
    }

    override fun getLayout(): Int {
        return R.layout.item_horizontal_scrolling
    }
}