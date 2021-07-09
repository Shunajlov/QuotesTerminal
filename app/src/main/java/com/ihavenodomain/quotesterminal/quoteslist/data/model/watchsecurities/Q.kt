package com.ihavenodomain.quotesterminal.quoteslist.data.model.watchsecurities

import com.google.gson.annotations.SerializedName

data class Q(
    @SerializedName("c")
    val c: String,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("chg")
    val chg: Int = 0,
    @SerializedName("ltp")
    val ltp: Int = 0,
    @SerializedName("ltr")
    val ltr: String = "",
    @SerializedName("pcp")
    val pcp: Int = 0,
)