package com.ihavenodomain.quotesterminal.quoteslist.domain.repository

import io.reactivex.rxjava3.core.Single

interface SecuritiesRepository {
    fun getTopSecurities(type: String, exchange: String, gainers: Int, limit: Int): Single<List<String>>
}