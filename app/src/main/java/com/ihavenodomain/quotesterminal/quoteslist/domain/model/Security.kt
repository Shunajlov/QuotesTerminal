package com.ihavenodomain.quotesterminal.quoteslist.domain.model

import com.ihavenodomain.quotesterminal.quoteslist.data.model.watchsecurities.Q

data class Security(
    /**
     * Ticker
     */
    val ticker: String,

    /**
     * Name of paper
     */
    val name: String = "",

    /**
     * Change in the price of the last deal in points relative to the closing price of the previous trading session
     */
    val priceChange: Int = 0,

    /**
     * Last trade price
     */
    val lastTradePrice: Int = 0,

    /**
     * Last trade rialto
     */
    val lastTradeRialto: String = "",

    /**
     * Percentage change relative to the closing price of the previous trading session
     */
    val percentChange: Int = 0,
) {

    fun calculatePayload(oldItem: Security): SecurityPayload? {
        val name = if (oldItem.name != this.name) this.name else null
        val priceChange = if (oldItem.priceChange != this.priceChange) this.priceChange else null
        val lastTradePrice = if (oldItem.lastTradePrice != this.lastTradePrice) this.lastTradePrice else null
        val lastTradeRialto = if (oldItem.lastTradeRialto != this.lastTradeRialto) this.lastTradeRialto else null
        val percentChange = if (oldItem.percentChange != this.percentChange) this.percentChange else null

        return if (name == null && priceChange == null
            && lastTradePrice == null && lastTradeRialto == null && percentChange == null
        ) {
            null
        } else {
            SecurityPayload(name, priceChange, lastTradePrice, lastTradeRialto, percentChange)
        }
    }

    companion object {

        fun createFromRaw(q: Q) =
            Security(q.c, q.name, q.chg, q.ltp, q.ltr, q.pcp)
    }
}