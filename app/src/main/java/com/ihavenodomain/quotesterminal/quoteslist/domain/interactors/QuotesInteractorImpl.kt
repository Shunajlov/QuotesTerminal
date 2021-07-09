package com.ihavenodomain.quotesterminal.quoteslist.domain.interactors

import com.ihavenodomain.quotesterminal.quoteslist.domain.model.Security
import com.ihavenodomain.quotesterminal.quoteslist.domain.repository.SecuritiesRepository
import io.reactivex.rxjava3.core.Single

class QuotesInteractorImpl(private val repository: SecuritiesRepository): QuotesInteractor {

    override fun getBaseSecuritiesTickersList(): Single<List<Security>> =
        repository.getTopSecurities("stocks", "russia", 0, 30)
            .map { securities ->
                securities.map { Security(ticker = it) }
            }
}