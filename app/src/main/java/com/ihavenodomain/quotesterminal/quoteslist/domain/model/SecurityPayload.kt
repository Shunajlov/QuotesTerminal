package com.ihavenodomain.quotesterminal.quoteslist.domain.model

data class SecurityPayload(
    val name: String? = "",
    val priceChange: Int? = 0,
    val lastTradePrice: Int? = 0,
    val lastTradeRialto: String? = "",
    val percentChange: Int? = 0,
)