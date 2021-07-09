package com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate

import com.ihavenodomain.quotesterminal.quoteslist.domain.model.Security

sealed class QuotesListState

object Loading: QuotesListState()

data class Error(val errorType: Errors): QuotesListState()

data class ShowList(val list: List<Security>): QuotesListState()
