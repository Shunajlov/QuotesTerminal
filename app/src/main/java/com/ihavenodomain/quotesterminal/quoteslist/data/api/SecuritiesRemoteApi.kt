package com.ihavenodomain.quotesterminal.quoteslist.data.api

import com.ihavenodomain.quotesterminal.quoteslist.data.model.topsecurities.TopSecuritiesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SecuritiesRemoteApi {
    @GET("api")
    fun getTopSecurities(
        @Query("q") q: String,
    ): Single<TopSecuritiesResponse>
}