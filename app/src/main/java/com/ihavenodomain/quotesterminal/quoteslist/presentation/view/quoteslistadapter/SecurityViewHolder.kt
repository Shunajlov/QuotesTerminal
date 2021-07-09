package com.ihavenodomain.quotesterminal.quoteslist.presentation.view.quoteslistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ihavenodomain.quotesterminal.R
import com.ihavenodomain.quotesterminal.quoteslist.domain.model.Security

class SecurityViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_security, parent, false)
) {

    private val tvTicker = itemView.findViewById<TextView>(R.id.tvTicker)

    fun setData(security: Security) {
        // do some data-to-view setting

        setTicker(security.ticker)
        setName(security.name)

    }

    private fun setTicker(ticker: String) {
        tvTicker.text = ticker
    }

    fun setName(name: String) {

    }
}