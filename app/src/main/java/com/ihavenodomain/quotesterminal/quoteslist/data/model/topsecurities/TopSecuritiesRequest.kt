package com.ihavenodomain.quotesterminal.quoteslist.data.model.topsecurities


import com.google.gson.annotations.SerializedName

data class TopSecuritiesRequest(
    @SerializedName("cmd")
    val cmd: String = "getTopSecurities",
    @SerializedName("params")
    val params: TopSecuritiesRequestParams
)