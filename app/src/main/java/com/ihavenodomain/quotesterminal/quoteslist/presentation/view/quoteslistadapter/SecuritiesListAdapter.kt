package com.ihavenodomain.quotesterminal.quoteslist.presentation.view.quoteslistadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ihavenodomain.quotesterminal.quoteslist.domain.model.Security
import com.ihavenodomain.quotesterminal.quoteslist.domain.model.SecurityPayload

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Security>() {
    override fun areContentsTheSame(oldItem: Security, newItem: Security): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Security, newItem: Security): Boolean {
        return oldItem.name == newItem.name
    }

    override fun getChangePayload(oldItem: Security, newItem: Security): Any? {
        return newItem.calculatePayload(oldItem)
    }
}

class SecuritiesListAdapter : ListAdapter<Security, SecurityViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecurityViewHolder {
        // we can set some click listeners (etc.) here
        return SecurityViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: SecurityViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        // if there's no difference, fill whole holder with data
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }

        payloads.filterIsInstance<SecurityPayload>().firstOrNull {
            it.name != null || it.priceChange != null || it.lastTradePrice != null
                    || it.lastTradeRialto != null || it.percentChange != null
        }?.let {
            // set different fields separately
            if (it.name != null) holder.setName(it.name)
        }
    }

    override fun onBindViewHolder(holder: SecurityViewHolder, position: Int) {
        holder.setData(getItem(position))
    }
}