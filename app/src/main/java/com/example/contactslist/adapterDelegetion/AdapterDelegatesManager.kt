package com.example.contactslist.adapterDelegetion

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.collection.size
import androidx.recyclerview.widget.RecyclerView

class AdapterDelegatesManager<T>(vararg delegates: AdapterDelegate<T>) {
    private val delegates: SparseArrayCompat<AdapterDelegate<T>> = SparseArrayCompat()

    init {
        for (element in delegates)
            addDelegate(element)
    }


    fun getItemViewType(items: List<T>, position: Int): Int {
        for (i in 0 until delegates.size) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i)
            }
        }
        throw NullPointerException("No Delegates for item at this position")
    }

    fun addDelegate(delegate: AdapterDelegate<T>) {
        var viewType = delegates.size()
        while (delegates[viewType] != null) {
            viewType++
        }
        delegates.put(viewType, delegate)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegate: AdapterDelegate<T> = delegates[viewType]
            ?: throw NullPointerException("No AdapterDelegates for this viewType")

        return delegate.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(
        items: List<T>,
        position: Int,
        holder: RecyclerView.ViewHolder
    ) {
        val delegate: AdapterDelegate<T> = delegates[holder.itemViewType]
            ?: throw NullPointerException("No delegate for item at position")

        delegate.onBindViewHolder(holder, items, position)
    }
}