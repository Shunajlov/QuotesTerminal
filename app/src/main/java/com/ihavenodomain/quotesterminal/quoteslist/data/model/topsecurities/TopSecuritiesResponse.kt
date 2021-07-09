package com.ihavenodomain.quotesterminal.quoteslist.data.model.topsecurities


import com.google.gson.annotations.SerializedName

data class TopSecuritiesResponse(
    @SerializedName("tickers")
    val tickers: List<String>
)