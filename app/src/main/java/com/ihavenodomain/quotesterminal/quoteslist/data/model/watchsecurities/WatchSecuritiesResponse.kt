package com.ihavenodomain.quotesterminal.quoteslist.data.model.watchsecurities


import com.google.gson.annotations.SerializedName

data class WatchSecuritiesResponse(
    @SerializedName("q")
    val q: List<Q>
)