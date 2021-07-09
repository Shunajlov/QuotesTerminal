package com.ihavenodomain.quotesterminal.quoteslist.data.repository

import com.google.gson.Gson
import com.ihavenodomain.quotesterminal.quoteslist.data.api.SecuritiesRemoteApi
import com.ihavenodomain.quotesterminal.quoteslist.data.model.topsecurities.TopSecuritiesRequest
import com.ihavenodomain.quotesterminal.quoteslist.data.model.topsecurities.TopSecuritiesRequestParams
import com.ihavenodomain.quotesterminal.quoteslist.domain.repository.SecuritiesRepository
import io.reactivex.rxjava3.core.Single

class SecuritiesRepositoryImpl(
    private val api: SecuritiesRemoteApi,
) : SecuritiesRepository {

    override fun getTopSecurities(type: String, exchange: String, gainers: Int, limit: Int): Single<List<String>> {
        val requestParams = TopSecuritiesRequestParams(type, exchange, gainers, limit)
        val requestJsonString = Gson().toJson(TopSecuritiesRequest(params = requestParams)).toString()

        return api.getTopSecurities(requestJsonString)
            .map { it.tickers }
    }
}