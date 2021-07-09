package com.ihavenodomain.quotesterminal.quoteslist.data.model.topsecurities


import com.google.gson.annotations.SerializedName

data class TopSecuritiesRequestParams(
    @SerializedName("type")
    val type: String, // stocks
    @SerializedName("exchange")
    val exchange: String, // russia
    @SerializedName("gainers")
    val gainers: Int, // 0
    @SerializedName("limit")
    val limit: Int, // 30
)