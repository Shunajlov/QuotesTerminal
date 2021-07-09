package com.ihavenodomain.quotesterminal.quoteslist.domain.interactors

import com.ihavenodomain.quotesterminal.quoteslist.domain.model.Security
import io.reactivex.rxjava3.core.Single

interface QuotesInteractor {
    fun getBaseSecuritiesTickersList(): Single<List<Security>>
}